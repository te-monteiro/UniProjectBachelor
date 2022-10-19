(* FiniteAutomata module body *)

(* 
Aluno 1: Pedro Oliveira 52967 mandatory to fill
Aluno 2: Vicente Cruz 52862 mandatory to fill

Comment:

?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????

*)

(*
01234567890123456789012345678901234567890123456789012345678901234567890123456789
   80 columns
*)

type symbol = char;;       (* our symbols are represented by chars *)
type word = symbol list;;  (* our words are represented by lists of symbols *)

type state = string;;      (* our states are represented by strings *)
type states = state list;;

type transition =
    state   (* state *)
  * symbol  (* consumed input symbol *)
  * state   (* next state *)
;;
type transitions = transition list;;

type fAutomaton = {
    initialState: state;       (* Initial state *)
    transitions: transitions;  (* Transition relation *)
    acceptStates: states       (* Accept states *)
};;


(* PRIVATE DEFINITIONS *)

let abc = {
    initialState = "START" ;
    transitions = [
            ("START",'a',"A"); ("START",'b',"START"); ("START",'c',"START");
                                                      ("START",'d',"START");
            ("A",'a',"A"); ("A",'b',"AB"); ("A",'c',"START"); ("A",'d',"START"); 
            ("AB",'a',"A"); ("AB",'b',"START"); ("AB",'c',"SUCCESS");
                                                    ("AB",'d',"START");
            ("SUCCESS",'a',"SUCCESS"); ("SUCCESS",'b',"SUCCESS");
                         ("SUCCESS",'c',"SUCCESS"); ("SUCCESS",'d',"SUCCESS")
        ];
    acceptStates = ["SUCCESS"]
};;

let abcND = {
    initialState = abc.initialState ;
    transitions = abc.transitions @ [
            ("SUCCESS",'a',"UNPRODUCTIVE");
            ("UNREACHABLE",'a',"SUCCESS");
            ("SUCCESS",'e',"UNPRODUCTIVE"); ("UNPRODUCTIVE",'a',"UNPRODUCTIVE")
        ];
    acceptStates = abc.acceptStates
};;

let canonical l =
    List.sort_uniq compare l
;;

let addAll symb =
    List.map (fun l -> symb::l)
;;

let flatMap f l =
    List.flatten (List.map f l)
;;

let map f l =
		List.map f l
;;

let partition p l =
		List.partition p l
;;

let find p l =
		List.find p l
;;

let exists a l =
		List.mem a l
;;

let size l =
	List.length l
;;

let rec determinismAux l =
	match l with
	| [] -> true
	| (x,y,z)::xs ->
if (List.length 
	(List.filter(fun (x1,y1,z1)-> x1 = x && y1 = y && z1 <> z)xs)) > 0
		then false
else determinismAux xs
;;

let rec reachableAux l allTrans =
	match l with
	| [] -> []
	| (x,y,z)::xs -> (reachableAux xs allTrans)
										@isReachable (List.filter(fun (x1,y1,z1) -> z1 = x)allTrans)
	and isReachable l =
		match l with
		| [] -> []
		| (_,_,z)::zs -> z::isReachable zs
;;   

let rec productiveAux iniS l accS =
	match l with
	| [] -> []
	| (x,y,z)::xs -> if x = iniS || z = iniS || List.mem z accS 
									then x::productiveAux iniS xs accS
									else productiveAux iniS xs accS 
								
let rec acceptAux allTrans iniS accS l w =
		match (w,l) with
		| [], _ -> if List.mem iniS accS then true else false
		| _, [] -> acceptAux allTrans iniS accS allTrans (List.tl w)
		| (y::ys), ((x1,y1,z1)::zs) -> if x1 = iniS && x1 <> z1 && y = y1 
															then acceptAux allTrans z1 accS allTrans ys 
															else acceptAux allTrans iniS accS zs w
;;

(*Usar filter*)
let rec minWord iniS lastS currS accS l =
	match l with
	| [] -> 0
	| (x,y,z)::xs-> if x = currS && x != z && List.mem z accS
									then 1 + minWord iniS x z accS xs
									else if x = currS && compare z currS != 0 && z != lastS
									then 1 + minWord iniS x z accS xs 
									else minWord iniS lastS currS accS xs 
;;
								
let size fa = 
	minWord fa.initialState fa.initialState 
	fa.initialState fa.acceptStates fa.transitions
;;
	
let rec generateAux n l alph size iniN =
	match l with
	| [] -> []
	| x::xs -> if n < size
				then []
				else if n = size
				then [generateNeededWord l size]
				else if n = iniN
				then generateAux (possible_cases n size) l alph size iniN
				else if (n mod 2) = 0
				then [(x::(generateNeededWord alph size))]@
				(generateAux (n-1) l alph size iniN)
				else if (n mod 2) != 0
				then [((generateNeededWord alph size)@[x])]@
				(generateAux (n-1) xs alph size iniN)
				else []
	and generateNeededWord l2 size =
		match l2 with
		| [] -> []
		| z::zs -> if size != 0 
							then z::generateNeededWord zs (size-1)
							else generateNeededWord zs size
	and possible_cases n size =
		(n-size) * (n-2) * 4 
;;

let rec accept2Aux fa iniS accS l w =
			match (w,l) with
		| [], _ -> if List.mem iniS accS then true else false
		| _, [] -> false
		| (y::ys), ((x1,y1,z1)::zs) -> if isAccepted fa iniS accS y 
		(List.filter(fun (x2,y2,z2) -> x2 = x1  && y2 = y)zs) ys 
										then accept2Aux fa z1 accS 
										(List.filter(fun (x2,y2,z2) -> y2 <> y)fa.transitions) ys
										else accept2Aux fa iniS accS zs w 
		and isAccepted fa currS accS letter l1 ys = 
			match l1 with
			| [] -> false
			| (x,y,z)::xs -> if acceptAux fa.transitions currS accS 
			(List.filter(fun (x1,y1,z1) -> (x1,y1,z1) <> (x,y,z))fa.transitions) [letter]
			then true
			else isAccepted fa currS accS letter l1 ys
;;

(* PUBLIC FUNCTIONS *)

let getAlphabet fa =
    canonical (map(fun (x,y,z) -> y)fa.transitions)
;;

let getStates fa =
    canonical (flatMap (fun (x,y,z) -> x::[z])fa.transitions)
;;

let gcut s ts =
    partition (fun (x,y,z) -> x = s || (x = s && x = z)) ts
;;

let determinism fa =
    determinismAux fa.transitions 
;;

let reachable fa =
    canonical (reachableAux fa.transitions fa.transitions)
;;

let productive fa =
    canonical (productiveAux fa.initialState fa.transitions fa.acceptStates)
;;

let accept w fa =
		acceptAux fa.transitions fa.initialState fa.acceptStates fa.transitions w 
;;

let generate n fa =
    canonical (generateAux n (getAlphabet fa) (getAlphabet fa) (size fa) n)
;;

let accept2 w fa =
    acceptAux fa.transitions fa.initialState fa.acceptStates fa.transitions w 
;;
