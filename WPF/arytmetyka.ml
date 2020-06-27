  (*JAKUB PANASIUK*)

  (* CODE REVIEWER : MATEUSZ ŁADYSZ *)


(* TYP *)

(*
typ liczbowy, który może się składać z 
1 przedziału (pary floatów) lub 2 przedziałów (2 pary par floatów)
*)
type wartosc = 
  | One of (float * float)
  | Two of (float * float) * (float * float);;

(* FUNKCJE POMOCNICZE *)

(* 
  funkcja, ktora tworzy przedzial od x do y i go zwraca
 *)
let sOne x y = 
  One (x, y);;

(* 
  funkcja, ktora tworzy przedzial od x do y i od z do k i je zwraca
 *)
let sTwo x y z k = 
  Two ((x, y), (z, k));;

(* 
  funkcja, ktora zwraca sumę dwóch zbiorów x i y
 *)
let rec polacz ( x : wartosc) ( y : wartosc) = 
  match x, y with
  | One (a, b), One (c, d) -> 
    if (a == nan) then One (c, d)
    else if (c == nan) then One (a, b)
    else if d < b then polacz y x
    else if (c > b) then Two ((a, b), (c, d))
    else One (min a c, max b d)
  | Two ((a, b), (c, d)), Two ((e, f), (g, h)) -> 
    polacz (One (min a e, max b f)) (One (min c g, max d h))
  | One (a, b), Two ((c, d), (e, f)) -> 
    if (a == nan) then Two ((c, d), (e, f))
    else if ( a = c) then polacz (One (a, max b d)) (One (e, f))
    else  polacz (One (c, d)) (One (min a e, f))
  | Two ((c, d), (e, f)), One (a, b) ->
    if (a == nan) then Two ((c, d), (e, f))
    else if ( a = c) then polacz (One (a, max b d)) (One (e, f))
    else  polacz (One (c, d)) (One (min a e, f));;

(* 
  funkcja, ktora zwraca minimalny element z 2 liczb
  z pominieciem wartosci nan
 *)
let mini a b = 
  if (classify_float a = FP_nan) then b
  else if (classify_float b = FP_nan) then a
  else if (a < b) then a
  else b;;
  
(* 
  funkcja, ktora zwraca maksymalny element z 2 liczb
  z pominieciem wartosci nan
 *)
let maxi a b = 
  if (classify_float a = FP_nan) then b
  else if (classify_float b = FP_nan) then a
  else if (a > b) then a
  else b;;

(* 
  funkcja, ktora sprawdza czy ktoras z 2 wartosci podanych na wejsciu jest nan'em
 *)
let czyNaN2 a b = 
  if a == nan || b == nan then true
  else false;;

(* 
  funkcja, ktora sprawdza czy ktoras z 4 wartosci podanych na wejsciu jest nan'em
 *)
let czyNaN4 a b c d = 
  if a == nan || b == nan || c == nan || d == nan then true
  else false;;

(* 
  funkcja, ktora sprawdza czy ktoras z 6 wartosci podanych na wejsciu jest nan'em
 *)
let czyNaN6 a b c d e f = 
  if a == nan || b == nan || c == nan || d == nan || 
     e == nan || f == nan then true
  else false;;

(* 
  funkcja, ktora sprawdza czy ktoras z 8 wartosci podanych na wejsciu jest nan'em
 *)
let czyNaN8 a b c d e f g h = 
  if a == nan || b == nan || c == nan || d == nan || 
     e == nan || f == nan || g == nan || h == nan then true
  else false;;
  
(* 
funkcja, ktora odwraca przedzial a, 
zeby przygotowac ow przedzial do mnozenia przez niego, i go zwraca
*)
let odwrotnosc (a : wartosc) = 
  match a with
  | One (a, b) ->
    if czyNaN2 a b || (a = 0. && b = 0.) then One (nan, nan)
    else if (b = 0.) then One (neg_infinity, (1. /. a))
    else if (a = 0.) then One (1. /. b, infinity)
    else if (0. >= a && 0. <= b) 
      then Two ((neg_infinity, 1. /. a), (1. /. b, infinity))
    else One ((min (1. /. b) (1. /. a)), (max (1. /. b) (1. /. a)))
  | Two ((a, b), (c, d)) ->
    if czyNaN4 a b c d then sOne nan nan
    else if (a = neg_infinity && b = 0.) 
      then One (neg_infinity, 1. /. c)
    else if (a < 0. && c = 0.) then One (1. /. a, infinity)
    else if not ( b > 0. || c < 0.) then One (1. /. b, 1./. c)
    else Two ((neg_infinity, 1. /. c), (1. /. b, infinity));;
    
(* KONSTRUKTORY *)

(*
funkcja, ktora zwraca przedział x +/- p%*x, przy dokładności p i zmiennej x
 *)
let wartosc_dokladnosc x p = 
  if ( (x -. (p /. 100.) *. x) <> nan && (x +. (p /. 100.) *. x) <> nan ) 
    then One ( min (x -. (p /. 100.) *. x) (x +. (p /. 100.) *. x),
               max (x -. (p /. 100.) *. x) (x +. (p /. 100.) *. x))
  else sOne nan nan;;

(* 
funkcja, ktora zwraca przedzial od (min a b) do (max a b) 
 *)
let wartosc_od_do a b = 
  if (a < b) then One (a, b)
  else One (b, a);;

(* 
  funkcja, ktora zwraca przedzial od x do x (dla uproszczenia implementacji przedzialow),
  lub jezeli x jest nieskonczony to zwraca wartosc nan 
 *)
let wartosc_dokladna x = 
  if (x <> infinity && x <> neg_infinity) then One (x, x)
  else One (nan, nan);;
   
(* SELEKTORY *)

(* 
  funkcja, ktora zwraca srednia arytmetyczna przedzialu od a do b
  jezeli zapytanie jest o przedzial skladajacy sie z 2 przedzialow
  to funkcja zwraca nan
 *)
let sr_wartosc (a : wartosc) = 
  match a with
  | One (c, d) -> (c +. d) /. 2.
  | Two ((_, _),( _, _)) -> nan;;  

(* 
  funkcja, ktora zwraca minimalna wartosc przedzialu lub 2 przedzialow
 *)
let min_wartosc (a : wartosc) =
  match a with
  | One (c, d) -> min c d;
  | Two ((c, d), (e, f)) -> min c (min d (min e f) );;

(* 
  funkcja, ktora zwraca maksymalna wartosc przedzialu lub 2 przedzialow
 *)
let max_wartosc (a : wartosc) =
  match a with
  | One (c, d) -> max c d;
  | Two ((c, d), (e, f)) -> max c (max d (max e f) );;

(* 
  funkcja, ktora odpowiada na zapytanie czy element x znajduje sie w 
  ktoryms z przedzialow podanych na wejsciu
 *)
let in_wartosc (a : wartosc) x = 
  match a with
  | One (c, d) -> if (x >= c && x <= d) then true else false
  | Two ((c, d), (e, f)) -> if ((x >= c && x <= d) || (x >= e && x <= f))
    then true else false;;
  


(* MODYFIKATORY *)

(* 
  funkcja, ktora zwraca sume dwoch przedzialow a i b, taką, że
  do każdej wartości z przedziału a dodajemy każdą wartość z przedziału b
 *)
let plus (a : wartosc) (b : wartosc) = 
  match a, b with
  | One (a, b), One (c, d) -> 
    if czyNaN4 a b c d then sOne nan nan
    else sOne (a +. c) (b +. d)
  | Two ((a, b), (c, d)), Two ((e, f), (g, h)) -> 
    if czyNaN8 a b c d e f g h then sOne nan nan
    else sOne neg_infinity infinity
  | One (a, b), Two ((c, d), (e, f)) -> 
    if czyNaN6 a b c d e f then sOne nan nan
    else if (d +. b >= e +. a) then sOne c (f +. b)
    else sTwo (c +. a) (d +. b) (e +. a) (f +. b)
  | Two ((c, d), (e, f)), One (a, b) -> 
    if czyNaN6 a b c d e f then sOne nan nan
    else if (d +. b >= e +. a) then sOne c (f +. b)
    else sTwo (c +. a) (d +. b) (e +. a) (f +. b);;

(* 
  funkcja, ktora zwraca roznice dwoch przedzialow a i b, 
  taką, że od każdej wartości z przedziału a odejmujemy każdą wartość z przedziału b
 *)
let minus (a : wartosc) (b : wartosc) = 
  match b with
  | One (c, d) -> plus a (sOne (-.d) (-.c))
  | Two ((c, d), (e, f)) -> plus a (sTwo (-.f) (-.e) (-.d) (-.c));;

(* 
  funkcja, ktora zwraca iloczyn dwoch przedzialow a i b
  taki, że każdą liczbę z przedziału a mnożymy przez każdą liczbę z przedziału b
 *)
let rec razy (a : wartosc) (b : wartosc) = 
  match a, b with
  | One (a, b), One (c, d)  -> 
    if czyNaN4 a b c d then sOne nan nan
    else if ((a = 0. && b = 0. && c = neg_infinity && d = infinity ) || 
    (c = 0. && d = 0. && a = neg_infinity && b = infinity)) then sOne 0. 0.

    else sOne (mini (a *. c) (mini (a *. d) (mini (b *. c) (b *. d)))) 
              (maxi (a *. c) (maxi (a *. d) (maxi (b *. c) (b *. d))))

  | One (a, b), Two ((c, d), (e, f))-> 
    let x1 = a *. c and x2 = a *. d and x3 = a *. e and x4 = a *. f
    and x5 = b *. c and x6 = b *. d and x7 = b *. e and x8 = b *. f in

    let a1 = One (mini x1 x2, maxi x1 x2)
    and a2 = One (mini x3 x4, maxi x3 x4)
    and b1 = One (mini x5 x6, maxi x5 x6)
    and b2 = One (mini x7 x8, maxi x7 x8) in
   
    if czyNaN6 a b c d e f then sOne nan nan
    
    else if (a < 0. && b > 0.)
      then polacz (razy (sOne a (0.)) (Two ((c, d), (e, f)))) 
                  (razy (sOne 0. b) (Two ((c, d), (e, f)))) 

    else if (maxi x1 x2 = maxi x5 x6 && maxi x1 x2 = infinity)
      then polacz (polacz (a1 : wartosc) (b1 : wartosc)) 
                  (polacz (a2 : wartosc) (b2 : wartosc))
    
    else polacz (polacz (a1 : wartosc) (b2 : wartosc)) 
                (polacz (a2 : wartosc) (b1 : wartosc))

  | Two ((c, d), (e, f)), One (a, b) -> 
    let x1 = a *. c and x2 = a *. d and x3 = a *. e and x4 = a *. f
    and x5 = b *. c and x6 = b *. d and x7 = b *. e and x8 = b *. f in

    let a1 = One (mini x1 x2, maxi x1 x2)
    and a2 = One (mini x3 x4, maxi x3 x4)
    and b1 = One (mini x5 x6, maxi x5 x6)
    and b2 = One (mini x7 x8, maxi x7 x8) in
    
    if czyNaN6 a b c d e f then sOne nan nan
  
    else if (a < 0. && b > 0.)
      then polacz (razy (sOne a (-0.)) (Two ((c, d), (e, f)))) 
                  (razy (sOne 0. b) (Two ((c, d), (e, f)))) 
    
    else if (maxi x1 x2 = maxi x5 x6 && maxi x1 x2 = infinity) 
      then polacz (polacz (a1 : wartosc) (b1 : wartosc)) 
                  (polacz (a2 : wartosc) (b2 : wartosc))
    
    else polacz (polacz (a1 : wartosc) (b2 : wartosc)) 
                (polacz (a2 : wartosc) (b1 : wartosc))

  | Two ((a, b), (c, d)), Two ((e, f), (g, h)) -> 
    if czyNaN8 a b c d e f g h then sOne nan nan
     else  polacz (polacz (razy (One (a, b)) (One (g, h))) (razy (One (c, d)) (One (e, f)))) 
                  (polacz (razy (One (a, b)) (One (e, f))) (razy (One (c, d)) (One (g, h))));;


(* 
  funkcja, ktora iloraz dwoch przedzialow a i b
  taki, że każdą wartośc z przedziału a dzieli przez każdą wartość z przedziału b,
  korzysta ona z odwróconego przedziału b i mnoży a i (1/b)
 *)
let podzielic (a : wartosc) (b : wartosc) =
  razy a (odwrotnosc b);;



(* TESTY *)
(*

let a = in_wartosc ( podzielic ( wartosc_od_do (-5.400000) (9.400000) ) ( wartosc_dokladnosc (-0.200000) (0.000000) ) ) (-1.800000);;
assert (a = true);;

let a = sr_wartosc ( plus ( wartosc_od_do (0.400000) (1.600000) ) ( wartosc_dokladna (-1.800000) ) ) ;;
assert (a = -0.799999999999999933);;

let a = in_wartosc ( podzielic ( wartosc_od_do (0.000000) (6.600000) ) ( wartosc_dokladnosc (8.000000) (0.200000) ) ) (-5.800000);;
assert (a = false);;

let a = sr_wartosc ( podzielic ( wartosc_dokladnosc (7.800000) (7.800000) ) ( wartosc_od_do (-2.000000) (5.600000) ) ) ;;
assert ((classify_float a) == FP_nan);;

let a = sr_wartosc ( plus ( wartosc_dokladna (6.800000) ) ( wartosc_dokladna (8.400000) ) ) ;;
assert (a = 15.2);;

let a = min_wartosc ( podzielic ( wartosc_dokladnosc (-1.800000) (6.800000) ) ( wartosc_od_do (0.000000) (7.400000) ) ) ;;
assert (a = neg_infinity);;

let a = sr_wartosc ( podzielic ( minus ( wartosc_dokladna (0.000000) ) ( podzielic ( wartosc_od_do (4.000000) (8.400000) ) ( plus ( wartosc_dokladnosc (4.600000) (7.400000) ) ( wartosc_dokladnosc (-6.400000) (2.800000) ) ) ) ) ( plus ( wartosc_dokladna (6.800000) ) ( wartosc_od_do (0.000000) (5.800000) ) ) ) ;;
assert (a = 0.55081598921208208);;

let a = max_wartosc ( podzielic ( wartosc_od_do (0.000000) (6.000000) ) ( wartosc_dokladna (4.000000) ) ) ;;
assert (a = 1.5);;

let a = in_wartosc ( plus ( minus ( wartosc_od_do (-1.000000) (4.000000) ) ( wartosc_dokladna (0.000000) ) ) ( podzielic ( wartosc_dokladna (0.000000) ) ( wartosc_od_do (0.000000) (5.000000) ) ) ) (9.000000);;
assert (a = false);;

let a = min_wartosc ( podzielic ( wartosc_od_do (-1.000000) (7.000000) ) ( wartosc_dokladnosc (0.000000) (1.000000) ) ) ;;
assert ((classify_float a) == FP_nan);;

let a = sr_wartosc ( plus ( wartosc_od_do (1.000000) (2.000000) ) ( wartosc_dokladna (5.000000) ) ) ;;
assert (a = 6.5);;

let a = in_wartosc ( razy ( wartosc_od_do (-8.000000) (-1.000000) ) ( podzielic ( wartosc_dokladna (0.000000) ) ( wartosc_dokladnosc (-10.000000) (6.000000) ) ) ) (-8.000000);;
assert (a = false);;

*)


