    (* JAKUB PANASIUK *)
    
    (* CODE REVIEWER: JAKUB ZACHARCZUK *)
    
(* typ drzewa, które jest jedną z dwóch opcji
  - Leafem - nie ma wartości ani dzieci
  - Node'em - ma w sobie kolejno: lewe poddrzewo, priorytet wierzchołka, prawą wysokość oraz prawe poddrzewo *)
type 'a queue = 
  | Node of 'a queue * 'a * int * 'a queue 
  | Leaf
  
(*  wyjątek zwracany, kiedy chcemy usunąć minimalny element z pustego drzewa *)
exception Empty
  
(* funkcja zwracająca puste drzewo *)
let empty = Leaf

(* funkcja sprawdzająca czy dane drzewo jest puste (Czyli czy jest leaf'em czy Node'em) *)
let is_empty t = 
  match t with
  | Leaf -> true
  | Node (_, _, _, _) -> false 

(* funkcja zwracająca prawą wysokość danego drzewa *)
let prawa_wysokosc t = 
  match t with
  | Leaf -> (-1)
  | Node (_, _, h, _) -> h
  
(* funkcja która dla zadanego drzewa zamienia miejscami jego poddrzewa
  kiedy prawa wysokość lewego jest mniejsza od prawego, funkcja aktualizuje również prawą wysokość
  drzewa i zwraca zmodyfikowane drzewo *)
let zamien_poddrzewa t =
  match t with
  | Leaf -> Leaf
  | Node (l, x, h, p) ->
    if prawa_wysokosc l > prawa_wysokosc p then
      Node (l, x, (prawa_wysokosc p) + 1, p)
    else Node (p, x, (prawa_wysokosc l) + 1, l)

(* funkcja łączy 2 drzewa w jedno spełniające kryteria drzewa lewicowego *)
let rec join t1 t2 = 
  match t1, t2 with
  | Leaf, Leaf -> Leaf
  | Node (l1, x1, h1, p1), Node (l2, x2, h2, p2) ->
    if x1 > x2 then join t2 t1 else
      zamien_poddrzewa (Node (l1, x1, 0, join p1 t2))
  | drz, Leaf -> drz
  | Leaf, drz -> drz

(* Funkcja, która na drzewie robi jedną z 2 rzeczy :
  - jeżeli drzewo jest puste to podnosi wyjątek
  - w przeciwnym wypadku zwraca minimalny element drzewa i 
    drzewo utworzone z lego lewego i prawego poddrzewa połączonego
    w jedno *)
let delete_min t = 
  let usun t = 
    match t with
    | Leaf -> raise Empty
    | Node (l, x, h, p) ->
      let tr = join l p in
        (x, tr) in
  usun t
  
(* Funkcja dodaje element e do drzewa t tak żeby drzewo zachowało swoją lewicowość *)
let add e t = 
  join (Node (Leaf, e, 0, Leaf)) t
  
  
(* TESTY *)

(*
let a = empty;;
assert (a = Leaf);;

let a = delete_min ( Node(Leaf, 5, 0, Leaf ));;
assert (a = (5, Leaf));;

let a = (add 5 empty);;
assert (a = Node(Leaf, 5, 0, Leaf));;

let a = is_empty empty;;
assert (a = true);;

let a = delete_min (add 5 (Node(Leaf, 7, 0, Leaf)));;
assert (a = (5, (Node(Leaf, 7, 0, Leaf))));;

let a = (add 5 (Node(Leaf, 7, 0, Leaf)));;
assert (a = (Node((Node(Leaf, 7, 0, Leaf)), 5, 0, Leaf)));;

let a = (add 105 (Node((Node(Leaf, 7, 0, Leaf)), 5, 0, Leaf)));;
assert (a = (Node((Node(Leaf, 105, 0, Leaf)), 5, 1, (Node(Leaf, 7, 0, Leaf)))));;

let a = delete_min (Node((Node(Leaf, 7, 0, Leaf)), 5, 1, (Node(Leaf, 105, 0, Leaf))));;
assert (a = (5, Node((Node(Leaf, 105, 0, Leaf)), 7, 0, Leaf)));;

let a = join (Node((Node(Leaf, 7, 0, Leaf)), 5, 0, Leaf)) (Node(Leaf, 105, 0, Leaf));;
assert (a = (Node((Node(Leaf, 105, 0, Leaf)), 5, 1, (Node(Leaf, 7, 0, Leaf)))));;

let a = is_empty (Node((Node(Leaf, 105, 0, Leaf)), 5, 1, (Node(Leaf, 7, 0, Leaf))));;
assert (a = false);;
*)
