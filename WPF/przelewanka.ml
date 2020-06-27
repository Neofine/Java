(* AUTOR : JAKUB PANASIUK
   CODE REVIEWER : MATEUSZ NOWAKOWSKI *)
   
(* Funkcja, która sprawdza nwd dwóch liczb a i b podanych na wejściu*)
let rec gcd a b = 
	if b = 0 then a
	else gcd b (a mod b)
    
(* Funkcja, dla danej tablicy z wejścia oraz tablicy pojemności kubków,
  sprawdza warunek konieczy do możliwości osiągnięcia ostatecznego wyniku,
  czyli każda pojemność którą chcemy osiągnąć musi bez reszty dzielić się przez
  nwd wszystkich pojemności maksymalnych z wejścia *)
let check_gcd t vol = 
  let gcd_of_an_array =  Array.fold_left gcd vol.(0) vol in
  (* funkcja zapożyczona od code reviewera *)
  Array.for_all
  (fun (a, b) -> 
     if b <> a 
       then b mod gcd_of_an_array = 0
		 else true) t 

(* Funkcja, która ma na celu optymalizację programu, dla danej na wejściu kolejno:
    tablicy maksymalnych pojemności, tablicy pojemności które chcemy osiągnąć i ilości pojemników
    sprawdza czy istnieje taka chciana pojemność która jest albo równa jej maksymalnej pojemności
    w danym kubku lub jest równa 0, jeżeli takiej nie ma to nigdy nie stworzymy chcianych pojemności *)
let check_glass vol desired length = 
  let existance = ref false in
  for i = 0 to length - 1 do
    if vol.(i) = desired.(i) || desired.(i) = 0 
      then existance := true;
  done;
  !existance
  
(* Funkcja, dla danego stanu rozpatrywanego i tablicy którą chcemy osiągnąc, sprawdza
   czy stan rozpatrywany jest równy stanowi który chcemy osiągnąć, jak jest
   zwraca true w przeciwnym przypadku false *)
let check_state state desired = 
  state = desired

(* Funkcja, która dla danych na wejściu: tablicy haszy, stan rozpatrywany, odległość od tego stanu,
  sprawdza czy dany stan jest już na tablicy haszy, jak tak to zwraca false, w przeciwnym przypadku
  dodaje odległość na state'owej pozycji *)
let can_i_add hash_table state dist =
  if not (Hashtbl.mem hash_table state) then
  (
    Hashtbl.add hash_table state dist;
    true
  )
  else false
  
(* Funkcja, dla zadanego stanu, indeksu szklanki oraz tablicy maksymalnych wielkości, ustawia
   w rozpatrywanym stanie ilość wody w x'tym kubku na maksymalną i zwraca zmodyfikowany stan *)
let fill state x vol = 
  let state_c = Array.copy state in
  state_c.(x) <- vol.(x);
  state_c
  
(* Funkcja, dla zadanego stanu oraz indeksu szklanki, ustawia
   w rozpatrywanym stanie ilość wody w x'tym kubku na 0 i zwraca zmodyfikowany stan *)
let empty state x = 
  let state_c = Array.copy state in
  state_c.(x) <- 0;
  state_c
  
(* Funkcja, dla zadanego stanu, indeksu modyfikowanego kubka, ilości kubków oraz tablicę maksymalnych pojemności
   próbuje z x'tego kubka przelać wodę do każdego innego i każdy z tych stanów wrzuca na listę, którą funkcja zwraca na wyjściu *)
let transfer state x length vol = 
  let states = ref [] in
  let state_c = Array.copy state in
  
  for i = 0 to length - 1 do
    if i <> x then
    (
      let state_cc = Array.copy state_c in
      if vol.(i) - state_c.(i) < state_c.(x) then
      (
        state_cc.(x) <- state_c.(x) - vol.(i) + state_c.(i);
        state_cc.(i) <- vol.(i);
        states := state_cc :: (!states);
      )
      else
      (
        state_cc.(i) <- state_c.(i) + state_c.(x);
        state_cc.(x) <- 0;
        states := state_cc :: (!states);
      )
    )
  done;
  !states
  
let przelewanka t = 
  let length = Array.length t in (* długość tablicy t *)
  let vol = Array.make length 0 in (* pierwsze argumenty z pary z tablicy t *)
  let desired = Array.make length 0 in (* drugie argumenty z pary z tablicy t *)
  let ans = ref (-1) in (* wynik ostateczny *)
  if length = 0 then ans := 0;
  
  (* przepisuję elementy z tablicy par na 2 osobne tablice dla wygody *)
  for i = 0 to length - 1 do
    vol.(i) <- fst t.(i);
    desired.(i) <- snd t.(i)
  done;
  
  (* Jeżeli spełnia 2 warunki konieczne to mogę zacząć poszukiwania wyniku *)
  if check_glass vol desired length && check_gcd t vol then
  (
    let state = Array.make length 0 in (* stan początkowy, czyli same 0 w tablicy *)
    let hash_t = Hashtbl.create 1000 in (* tablica haszy *)
    let sque = Queue.create () in (* kolejka w której będę kolejne stany przechowywał *)
    
    Hashtbl.add hash_t state 0;
    Queue.push state sque;
    
    if check_state state desired then ans := 0; (* sprawdzam czy wszystkie chciane pojemności są równe 0, jak tak to od razu mam wynik *)
    
    while not (Queue.is_empty sque) && !ans = -1 do
      let state_now = Queue.pop sque in
      let dist = Hashtbl.find hash_t state_now + 1 in
      
      for i = 0 to length - 1 do
        let state1 = fill state_now i vol in (* stan w którym wypełniam i'ty kubek *)
        let state2 = empty state_now i in (* stan w którym opróżniam i'ty kubek *)
        let state_list = transfer state_now i length vol in (* tablica stanów w których z i'tego kubka próbuję przelać wodę do każdego innego *)
        
        if can_i_add hash_t state1 dist then
        (
          if check_state state1 desired then ans := dist;
          Queue.push state1 sque
        );
        
        if can_i_add hash_t state2 dist then
        (
          if check_state state2 desired then ans := dist;
          Queue.push state2 sque;
        );
        
        List.iter
          (fun x ->
            if can_i_add hash_t x dist then
            (
              if check_state x desired then ans := dist;
              Queue.push x sque
            ) )
           state_list;
      done;
    done;
  );
  
  !ans
  
(* TESTY *)

(*
let c = [|(3,2);(3,3);(1,0);(12,1)|];;
assert ( przelewanka c = 4 );;
let c = [|(1,1);(100,99)|];;
assert ( przelewanka c = 2 );;
let c = [|(3,3);(5,4);(5,2);(6,1)|];;
assert (przelewanka c = 6);;
let c = [|(100,3);(2,1);(1,0);(6,1)|];;
assert (przelewanka c = 7);;
let c = [|(3,3);(5,5);(5,5);(6,6)|];;
assert (przelewanka c = 4);;
let c = [|(40,20);(20,10);(10,5);(5,0)|];;
przelewanka c ;;
let c = [|(19,3);(1,1);(2,2)|];;
assert (przelewanka c = 6);;
let c = [|(14,3);(3,1);(3,0)|];;
assert (przelewanka c = 13);;
let c = [|(3,3);(4,0);(1,1);(6,6)|];;
assert (przelewanka c = 3);;
let c = [|(46,20);(23,10);(13,5);(5,0)|];;
assert (przelewanka c = 10);;
let c = [|(18,3);(3,1);(2,2)|];;
assert (przelewanka c = 4);;
let c = [|(14,3);(5,1)|];;
assert (przelewanka c = -1);;
let c = [|(14,3);(5,1);(5,0)|];;
assert (przelewanka c = 16);;
*)
