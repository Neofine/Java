(* AUTOR : JAKUB PANASIUK
  CODE REVIEWER: MIŁOSZ PIEKUTOWSKI 
  *)

(* typ punkt, który jest reprezentowany przez 2 floaty które są interpretowane jako współrzędne x i y *)
type point = float * float

(* poskładana kartka, ile razy szpilka wbita w danym punkcie przebije tą kartkę *)
type kartka = point -> int

(* funkcja odpowiada na zapytanie czy punkt (x, y) zawiera się w prostokącie zadanym przez (x1, y1) i (x2, y2) *)
let prostokat (x1, y1) (x2, y2) (x, y) = 
  if x >= x1 && x <= x2 && y >= y1 && y <= y2 then 1
  else 0 

(* funkcja odpowiada na zapytanie czy punkt (x, y) zawiera się w kole o środku (x, y) ktore ma promien r *)
let kolko (x1, y1) r (x, y) = 
  if (abs_float(x -. x1)) *. (abs_float(x -. x1)) +. (abs_float(y -. y1)) *. (abs_float(y -. y1)) <= r *. r then 1
  else 0

(* funkcja zwraca punkt odbity symetrycznie punkt (x, y) przez prostą wyznaczoną przez 2 punkty (x1, y1) (x2, y2) *)
let sym (x1, y1) (x2, y2) (x, y) =
  if x1 = x2 then (2. *. x1 -. x, y)
	else if y1 = y2 then (x, 2. *. y1 -. y)
	else
		let xf = ((y -. (x2 -. x1) /. (y1 -. y2) *. x) -. (y2 -. (y1 -. y2) /. (x1 -. x2)*. x2))
		      /. ((y1 -. y2) /. (x1 -. x2) -. (x2 -. x1) /. (y1 -. y2)) in
    let yf = (y1 -. y2) /. (x1 -. x2) *. xf +. (y2 -. (y1 -. y2) /. (x1 -. x2)*. x2) in
	  (2. *. xf -. x, 2. *. yf -. y)

(* funkcja zwraca po której stronie prostej wyznaczonej przez 2 punkty (x1, y1) (x2, y2) znajduje się punkt (x, y):
    - 0 jeżeli punkt jest na prostej
    - 1 jeżeli punkt jest po prawej stronie prostej ( wtedy w funkcji zloz pomijamy ten punkt bo nie przebija on złożonej kartki )
    - -1 jeżeli punt jest po lewej stronie prostej ( wtedy w funkcji zloz liczymy ten punkt oraz punkt symetryczny do niego )
 *)
let ktora_strona (x1, y1) (x2, y2) (x, y) =
  let wyz = ((x -. x1) *. (y2 -. y1)) -. ((y -. y1) *. (x2 -. x1))
  in if abs_float wyz <= 1e-12 then 0
  else if wyz < 0. then -1
  else 1

(* funkcja sklada kartke k wedlug prostej zadanej przez punkty a i b 
   i daje na wyniku odpowiedź ile szpilka wbita w dany punkt (po złożeniu) przebije warstw kartki *)
let zloz a b k pkt =
  match ktora_strona a b pkt with
  |0 -> k pkt
  |1 -> 0
  |_ -> k pkt + k (sym a b pkt)

(* funkcja sklada kartke k wedlug tablicy prostych zadanych przez punkty a i b *)
let skladaj l k = 
  List.fold_left (fun acc (a, b) -> zloz a b acc) k l

(* TESTY *)
(*
;;
let kolo = kolko (0.,0.) 10. in

assert (kolo (1000., 0.) == 0);

let poziomo = zloz (0.,0.) (1.,0.) kolo in

assert (poziomo (0.,0.) == 1);

assert (poziomo (0.,1.) == 2);

assert (poziomo (0.,-1.) == 0);;

let op=[((3.0,7.0),(1.0,1.0));((3.0,10.0),(2.0,3.0));((1.0,8.0),(4.0,7.0));((7.0,3.0),(3.0,8.0))];;

let kartka=kolko (5.,5.) 4. ;;

let test4=skladaj op kartka;;

if(test4(3.0,7.0) <> 4) then assert false;;

let kolo = kolko (0.,0.) 10.;;

if(kolo (1000., 0.) <> 0) then assert false;;

let poziomo = zloz (0.,0.) (1.,0.) kolo;;

if(poziomo (0.,0.) <> 1) then assert false;;

if(poziomo (0.,1.) <> 2) then assert false;;

if(poziomo (0.,-1.) <> 0) then assert false;;
*)
