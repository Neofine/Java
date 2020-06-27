(*        AUTOR : JAKUB PANASIUK 
  CODE REVIEWER : HUBERT GROCHOWSKI *)
  
open PMap

(* wyjątek zwracany przez program jeżeli w podanej na wejściu liście znajduje się cykl *)
exception Cykliczne
 
(* Funkcja, która sprawdza czy podana lista może być posortowana topologicznie, jeżeli nie
to podnosi exception, w przeciwnym wypadku zwraca posortowaną topologicznie listę wierzchołków *)
let topol l =
  let na_ktory = ref empty in (* mapa na której będę wykonywał przenumerowanie wierzchołków, żeby nie tworzyć nie potrzebnie dużej tablicy sąsiedztwa *)
  let z_ktorego = ref empty in (* mapa na której przechowywane będą pierwotne wartości przenumerowanych wierzchołków *)
  let n = ref 0 in (* wartość która reprezentuje ilość elementów na liście *)
 
  (* funkcja, która dla danej wartości wstawia na mapę jego przenumerowaną wartość*)
  let dodaj_na_mape a =
    if mem a !na_ktory = false then
    begin
      na_ktory := add a !n !na_ktory;
      z_ktorego := add !n a !z_ktorego;
      n := !n + 1;
    end
  in
  (* Funkcja dodaje na mapę wierzchołek oraz iteruje się po jego sąsiadach żeby ich również dodać*)
  let dodaj_element (a, b) =
    dodaj_na_mape a;
    List.iter dodaj_na_mape b;
  in
  (* Funkcja dla zadanej na wejściu listy wstawia wszystkie jej elementy na mapę *)
  let zmapuj_liste l =
    List.iter dodaj_element l
  in
  zmapuj_liste l;
 
  let l_sasiedztwa = Array.make !n [] in (* tworzę tablicę sąsiedztwa żeby reprezentować graf podany na wejściu *)
 
  (* Funkcja dla zadanego wierzchołka dodaje jego dzieci do jego tablicy sąsiedztwa *)
  let dodaj_element_listy (a, b) =
  let id_a = find a !na_ktory in
  List.iter (fun x ->
    l_sasiedztwa.(id_a) <- (find x !na_ktory) :: l_sasiedztwa.(id_a) ) b;
  in
  (* Funkcja, która z wszystkich wierzchołków na liście tworzy graf na tablicy sąsiedztwa *)
  let dodaj_liste l =
    List.iter dodaj_element_listy l
  in
  dodaj_liste l;
 
  let kolor = Array.make !n 0 in  (* tablica która będzie przechowywała kolory wierzchołków, 0 - nie odwiedzony, 1 - przetwarzany, 2 - przetworzony *)
  let wynik = ref [] in (* lista na której będzie przechowywany wynik końcowy programu *)
 
  (* Funkcja, która dla danego wierzchołka robi przeszukiwanie w głąb i sprawdza czy graf jest cykliczny
  oraz wrzuca na listę wynikową przeglądany wierzchołek jeżeli wszystko się zgadza *)
  let rec dfs x =
    if kolor.(x) = 1 then
      raise Cykliczne
    else if kolor.(x) = 0 then
    begin
    kolor.(x) <- 1;
    List.iter (fun u -> dfs u) l_sasiedztwa.(x);
    kolor.(x) <- 2;
    wynik := (find x !z_ktorego) :: !wynik
    end;
  in
  (* iteruję się po każdym nie odwiedzonym elemencie żeby wywołać na nim algorytm dfs *)
  for i = 0 to !n - 1 do
    if kolor.(i) = 0 then dfs i
  done;
  !wynik;;

(* TESTY *)

(*
exception WA;;
let test graph order =
  let hashtbl = Hashtbl.create (List.length order)
  in
  List.iteri (fun i x -> Hashtbl.add hashtbl x i) order;
  let check_one (v, l) =
    List.iter (fun u ->
      if (Hashtbl.find hashtbl v) > (Hashtbl.find hashtbl u)
      then raise WA;) l
  in
  try (List.iter check_one graph; true)
  with WA -> false;;

let test_cyclic g =
  try let _ = topol g in false
  with Cykliczne -> true
  
  let g = [
  (1, [2; 4; 8]);
  (2, [16; 32]);
  (4, [64; 128]);
  (8, [256; 512]);
  (16, [1024]);
  (32, [2048]);
  (64, [4096]);
  (128, [8192]);
  (256, [16384]);
  (512, [32768]);
];;

assert(test g (topol g));;

let g = [
  (11, [12]);
  (12, [13]);
  (7, [8]);
  (8, [9]);
  (1, [2]);
  (13, [6]);
  (3, [4]);
  (5, [6]);
  (6, [7]);
  (10, [11])
];;

assert(test g (topol g));;

let g = [
  (1, [2]);
  (3, [4]);
  (4, [1]);
  (5, [6]);
  (6, [3])
];;

assert(test g (topol g));;

let g = [
  (1, [2; 3; 4]);
  (3, [7; 8]);
  (4, [9; 10]);
  (10, [15; 16]);
  (2, [5; 6]);
  (13, [4; 10]);
  (11, [12]);
  (12, [13; 14])
];;

assert(test g (topol g));;

let g = [
  ("pole", ["pole"; "lyse"; "pole"])
];;

assert(test_cyclic g);;

let g = [
  ("tu", ["tudu"]);
  ("tudu", ["tudu"; "tudu"; "tudu"])
];;

let g = [
  ('a', ['e']);
  ('b', ['a'; 'c']);
  ('c', ['a']);
  ('e', [])
];;
*)
