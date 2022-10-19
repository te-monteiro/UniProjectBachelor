let f1 x = x + 1;;
f1 2;;
let f2 x = (f1 x);;
f2 2;;
let f3 x = 1 + x 5;;
f3 1;;
let f4 x y = x < y x;;

let rec succAll l =
		match l with
			[] -> [] 
		|	x::xs -> (x+1)::(succAll xs)
;; 

let rec belongs y l =
		match l with
			[] -> false
		| x::xs -> if y = x then true else belongs y xs (* ou x = y || belongs y xs *)
;; 

let rec union l1 l2 =
		match l1 with
			[] -> l2
		| x::xs -> if belongs x l2 then union xs l2 else x::union xs l2
;;

let rec inter l1 l2 =
		match l1 with
			[] -> []
		| x::xs -> if belongs x l2 then  x::inter xs l2 else inter xs l2
;;

let rec diff l1 l2 =
		match l1 with
			[] -> []
		| x::xs -> if belongs x l2 then diff xs l2 else x::diff xs l2
;;

let rec insert y ll =
		match ll with
			[] -> []
		| l::ls -> (y::l)::insert y ls
;;


let rec power l =
		match l with
		 	[] -> [[]]
		|	x::xs -> power xs @ insert x (power xs)
;;








			