(*
 * PSet - Polymorphic sets
 * Copyright (C) 1996-2019 Xavier Leroy, Nicolas Cannasse, Markus Mottl
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version,
 * with the special exception on linking described in file LICENSE.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *)
 
(* AUTOR: JAKUB PANASIUK
 CODE REVIEWER : MICHAŁ LESZCZYŃSKI*)

(* 
  typ set zawiera kolejno:
    - lewe poddrzewo
    - przedział wierzchołka
    - prawe poddrzewo
    - maksymalną głębokość poddrzewa tego wierzchołka + 1
    - ujemny rozmiar całego poddrzewa (wliczając w to każdy punkt w przedziale poddrzewa),
      operuję tu na liczbach ujemnych gdyż mieszczą więcej niż dodatnie int64
 *)
type set =
  | Empty
  | Node of  set * (int*int) * set * int * int64

(*
  typ t zawiera compare'ator który dla dwóch przedziałów zwraca
  1 jeżeli przedział podany jako drugi na wejściu jest większy
  0 jeżeli są równe
  -1 w przeciwnym wypadku
  zawiera również set'a
  *)
type 'k t =
  {
    cmp : (int * int) -> (int * int) -> int;
    set : set;
  }

(* funkcja zwraca maksymalną wysokość drzewa tego wierzchołka *)
let height = function
  | Node (_, _, _, h, _) -> h
  | Empty -> 0

(* funkcja zwraca rozmiar wszystkich elementów drzewa tego wierzchołka *)
let size = function
  | Node (_, _, _, _, s) -> s
  | Empty -> 0L

(* funkcja zwraca ujemną ilość elementów w przedziale *)
let amount (a, b) = 
  Int64.pred (Int64.sub (Int64.of_int(a)) (Int64.of_int(b)))

(* 
  funkcja zwraca set'a w którym jest w wierzchołku przedział k, a jego poddrzewami są
  l i r, funkcja również update'uje wszystkie zmienne które trzymam na secie
 *)
let make l k r = 
  Node (l, k, r, max (height l) (height r) + 1, Int64.add (Int64.add (size l) (size r) ) (amount k))
  
(* funkcja bierze set'a i zwraca poprawnie zbalansowanego set'a zakładając, że 
  różnica glebokosci na wejsciu jest <= 3*)
let bal l k r =
  let hl = height l in
  let hr = height r in
  if hl > hr + 2 then
    match l with
    | Node (ll, lk, lr, _, _) ->
        if height ll >= height lr then make ll lk (make lr k r)
        else
          (match lr with
          | Node (lrl, lrk, lrr, _, _) ->
              make (make ll lk lrl) lrk (make lrr k r)
          | Empty -> assert false)
    | Empty -> assert false
  else if hr > hl + 2 then
    match r with
    | Node (rl, rk, rr, _, _) ->
        if height rr >= height rl then make (make l k rl) rk rr
        else
          (match rl with
          | Node (rll, rlk, rlr, _, _) ->
              make (make l k rll) rlk (make rlr rk rr)
          | Empty -> assert false)
    | Empty -> assert false
  else make l k r

(* funkcja zwraca element najmniejszy w niepustym secie *)
let rec min_elt = function
  | Node (Empty, k, _, _, _) -> k
  | Node (l, _, _, _, _) -> min_elt l
  | Empty -> raise Not_found

(* funkcja usuwa element najmniejszy w niepustym secie *)
let rec remove_min_elt = function
  | Node (Empty, _, r, _, _) -> r
  | Node (l, k, r, _, _) -> bal (remove_min_elt l) k r
  | Empty -> invalid_arg "PSet.remove_min_elt"

(* funkcja bierze 2 drzewa i zwraca je scalone i zbalansowane, jeżeli
   różnica wysokości jest >= 3 to zwrócone drzewo nie musi być zbalansowane *)
let merge t1 t2 =
  match t1, t2 with
  | Empty, _ -> t2
  | _, Empty -> t1
  | _ ->
      let k = min_elt t2 in
      bal t1 k (remove_min_elt t2)
      
(* funkcja tworzy pustego seta z danym komparatorem *)
let create cmp = { cmp = cmp; set = Empty }

(* funkcja zwraca zbiór pusty *)
let empty = { cmp = compare; set = Empty }

(* funkcja zwraca czy podany set jest pusty *)
let is_empty x = 
  x.set = Empty
  
(* 
  funkcja dodaje przedzial x do drzewa jeżeli dany przedział
  nie ma elementów wspólnych z drzewem, w przeciwnym wypadku
  wyjście nie będzie drzewem avl
  *)
let rec add_one cmp x = function
  | Node (l, k, r, h, s) ->
      let c = cmp x k in
      if c = 0 then Node (l, x, r, h, s)
      else if c < 0 then
        let nl = add_one cmp x l in
        bal nl k r
      else
        let nr = add_one cmp x r in
        bal l k nr
  | Empty -> Node (Empty, x, Empty, 1, amount x)


(*
  funkcja bierze l v r, 
  l, r to drzewa a v to przedział i zwraca avl'a zawierającego w sobie
  l v i r
*)
let rec join cmp l v r =
  match (l, r) with
  | (Empty, _) -> add_one cmp v r
  | (_, Empty) -> add_one cmp v l
  | (Node(ll, lv, lr, lh, _), Node(rl, rv, rr, rh, _)) ->
      if lh > rh + 2 then bal ll lv (join cmp lr v r) else
      if rh > lh + 2 then bal (join cmp l v rl) rv rr else
      make l v r


(*
  funkcja zwraca (l, if, r) gdzie l - wszystkie przedziały mniejsze od x'a
  if - czy x jest zawarty w którymś przedziale drzewa
  r - wszystkie przedziały większe od x'a
*)
let split x { cmp = cmp; set = set } = 
  let includes x (a, b) = 
    if x >= a && x <= b then true
    else false
  in
  let rec loop x = function
    |Empty -> (Empty, false, Empty)
    | Node (l, v, r, _, _) ->
        let c = cmp (x, x) v in
        if includes x v then
          ((if (fst v < x) then add_one cmp (fst v, x-1) l else l), 
          true,
          (if (snd v > x) then add_one cmp (x+1, snd v) r else r))
        else if c < 0 then
          let (ll, pres, rl) = loop x l in (ll, pres, join cmp rl v r)
        else
          let (lr, pres, rr) = loop x r in (join cmp l v lr, pres, rr)
  in
  let setl, pres, setr = loop x set in
  { cmp = cmp; set = setl }, pres, { cmp = cmp; set = setr }

(* 
  funkcja bierze na wejście comparator i dwa drzewa i zwraca
  zbalansowane drzewo avl stworzone ze scalenia dwóch drzew z wejścia
  warunek t1 < t2
  *)
let rec realMerge cmp t1 t2 =
    match t1, t2 with
  | Empty, _ -> t2
  | _, Empty -> t1
  | _ ->
      let k = min_elt t2 in
      join cmp t1 k (remove_min_elt t2)
      
(* funkcja usuwa z drzewa przedział x i zwraca zbalansowane drzewo avl *)
let remove x { cmp = cmp; set = set } = 
  let (left, _, _) = split (fst x) { cmp = cmp; set = set } and
  (_, _, right) = split (snd x) { cmp = cmp; set = set } in
  {cmp = cmp; set = realMerge cmp left.set right.set}
  
(* 
  funkcja która dla podanego x'a i set'u zwraca:
    -jeżeli x zawiera się w którymś przedziale to zwraca ten przedzial
    -wpp zwraca niepoprawny przedzial (max_int, min_int
    *)
let rec expand x set = 
  let not_found = (max_int, min_int) in
  match set with
  | Empty -> not_found
  | Node (l, cur, r, _, _) ->
    let (left, right) = cur in
    if left <= x && x <= right then (left, right)
    else if x < left then expand x l
    else expand x r

(* funkcja bierze przedział x i set'a i zwraca zbalansowane drzewo avl powiększone o przedział x *)
let add x { cmp = cmp; set = set }=
  let l = 
    if fst x = min_int then fst x
    else min (fst x) (fst (expand ((fst x) - 1) set)) in
  let r = 
    if snd x = max_int then snd x 
    else max (snd x) (snd (expand ((snd x) + 1) set)) in
  let (lset, _, _) = split l { cmp = cmp; set = set } and
  (_, _, rset) = split r { cmp = cmp; set = set } in 
  {cmp = cmp; set = join cmp lset.set (l, r) rset.set}
    
(* funkcja dla zadanego elementu x zwraca ile jest elementów mniejszych od niego, jeżeli jest ich więcej niż
    max_int to zwraca max_int *)
let below x { cmp = cmp; set = set } = 
  let (lesser, _, _) = if x <> max_int then split (x+1) { cmp = cmp; set = set } else ({ cmp = cmp; set = set }, false, empty) in
  if Int64.neg (Int64.of_int(max_int)) < size lesser.set then Int64.to_int (Int64.neg(size lesser.set))
  else max_int
  
(* funkcja dla zadanego elementu x sprawdza czy znajduje się on w drzewie *)
let mem x { cmp = cmp; set = set } = 
  let (_, does, _) = split x { cmp = cmp; set = set } 
  in does

(* funkcja exists to funkcja mem o zmienionej nazwie, wymagająca tych samych elementów *)
let exists = mem

(* funkcja wykonuje funkcję f na kazdym elemencie zbioru*)
let iter f { set = set } =
  let rec loop = function
    | Empty -> ()
    | Node (l, k, r, _, _) -> loop l; f k; loop r in
  loop set


(* funkcja sklada funkcje f na przedzialach ze zbioru*)
let fold f { cmp = cmp; set = set } acc =
  let rec loop acc = function
    | Empty -> acc
    | Node (l, k, r, _, _) ->
          loop (f k (loop acc l)) r in
  loop acc set


(* funkcja zwraca posortowaną rosnąco listę elementow zbioru*)
let elements { set = set } = 
  let rec loop acc = function
      Empty -> acc
    | Node(l, k, r, _, _) -> loop (k :: loop acc r) l in
  loop [] set

(* 
        TESTY
        
let s = add (1, 1) (add (15, 16) (add (10, 14) (add (6, 9) empty)));;
assert((mem 10 (remove (10, 10) s)) = false);;
assert(is_empty (remove (1, 20) s));;
assert(mem 7 (remove (8, 15) s));;

let a = empty
let a = add (17, 20) a
let a = add (5, 8) a
let a = add (1, 2) a
let a = add (10, 12) a
let a = add (28, 35) a
let a = add (22, 23) a
let a = add (40, 43) a
let a = add (37, 37) a;;
assert((is_empty a) = false);;
assert(mem 29 a);;
assert(mem 21 a = false);;
assert(mem 38 a = false);;

let a = add (0, 5) empty;;
let a = add (7, 8) a;;
let a = add (-3, -3) a;;
let a = add (10, 13) a;;
assert(elements a = [(-3, -3); (0, 5); (7, 8); (10, 13)]);;
assert(below 8 a = 9);;
let b = add (6, 6) a;;
let b = remove (6, 6) b;;
let b = add (-100, -5) b;;
let b = add (-4, 6) b;;
assert(elements b = [(-100, 8); (10, 13)]);;
assert(below 10 b = 110);;

*)
