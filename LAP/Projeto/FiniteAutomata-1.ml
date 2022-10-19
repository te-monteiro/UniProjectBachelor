(* FiniteAutomata module body *)

(* 
Aluno 1: 52288 
Aluno 2: 52515 

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
						("START",'d',"START"); ("A",'a',"A"); ("A",'b',"AB"); 
						("A",'c',"START"); ("A",'d',"START"); ("AB",'a',"A"); 
						("AB",'b',"START"); ("AB",'c',"SUCCESS"); ("AB",'d',"START");
            ("SUCCESS",'a',"SUCCESS"); ("SUCCESS",'b',"SUCCESS"); 
						("SUCCESS",'c',"SUCCESS"); ("SUCCESS",'d',"SUCCESS")
        ];
    acceptStates = ["SUCCESS"]
};;

let dina2 = {
		initialState = "START" ;
   transitions = [
            ("START",'a',"A"); ("START",'b',"A"); ("START",'c',"START"); 
            ("START",'a',"B"); ("START",'c',"B"); 
            ("B",'c',"B"); ("B",'a',"DINA"); 
						("B",'b',"DINA"); ("B",'c',"DINA");   
						("DINA",'a',"DINA"); ("DINA",'b',"DINA"); ("DINA",'c',"B"); 
            ("A",'c',"A"); ("A",'a',"SUCCESS"); 
            ("A",'b',"SUCCESS"); ("A",'c',"SUCCESS"); 
						("SUCCESS",'a',"SUCCESS"); ("SUCCESS",'b',"SUCCESS"); ("SUCCESS",'c',"SUCCESS")
        ];
    acceptStates = ["SUCCESS"; "DINA"]
}

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

(* returns list with symbols of transitions *)
let rec getSymbols transitions = 
	match transitions with 
	| [] -> []
	| (_,x,_) :: xs -> x :: getSymbols xs
;;

(* returns list  of all states in the transitions *)
let rec getStateList transitions = 
	match transitions with 
	| [] -> []
	| (x,_,y) :: xs -> (x :: [y]) @ getStateList xs
;;

let rec existTransitions ts = 
	match ts with
	| [] -> true
	| (x,y,_)::xs -> 
				not( List.exists (fun(a,b,_)-> (a=x && b = y)) xs || 
				existTransitions xs) 
;;

let rec reaches s ts = 
	let (reachedStates, otherStates) = List.partition (fun(x,_,_) -> x = s) ts in 
	(* cant use g cut because function is only declared later *)
	[s] @ perState reachedStates otherStates
	(* ts1 are the transition states we want as initial transitions *)
	(* (first element of each triplet is always the same), *)
	(* ts2 are the remainind states we need to check against*)
	and perState ts1 ts2 =	
		match ts1 with
		| [] -> []
		| (_,_,z) :: xs -> [z] @ reaches z ts2 @ perState xs ts2 
;;

(* checks if any element in l1 is contained in l2 *)
let rec elementContained l1 l2 = 
	match l1 with
	| [] -> false
	| x :: xs -> (List.exists (fun(a) -> a = x) l2 || elementContained xs l2)
;;

(*ts are the stransitions of the FA and aS are the acceptedStates of the FA*)
let rec efficient ts aS = 
	match ts with
	| [] -> []
	| (x,_,_) :: xs -> if (elementContained (reaches x ts) aS) then
											x :: efficient xs aS
										else 
											efficient xs aS
;;

let rec checkWord w fa s =
	match w with
	| [] -> List.exists (fun (m) -> m = s) fa.acceptStates
	| x :: xs -> try
  							let (a,b,c) = 
									List.find (fun(y,z, _) -> y = s && z = x) fa.transitions in
  							checkWord xs fa c
  							with
  							| Not_found -> false
;;

let rec generateAux ws alph n =
  if (n = 0) then
  	[]
  else if (n = 1) then 
		ws 
	else
		let newWords = addLetter ws alph n in
		generateAux newWords alph (n-1)
  and addLetter words alphabet n =
  	match alphabet with
  	| [] -> []
  	| y::ys -> addAll y words @ addLetter words ys n
;;

let rec accept2Aux w fa ts =
	match ts with
	| [] -> checkWord w fa fa.initialState
	| x :: xs -> let newFa = { initialState = fa.initialState;
		transitions = xs; acceptStates = fa.acceptStates} in
		checkWord w fa fa.initialState || accept2Aux w newFa xs
;;

(* PUBLIC FUNCTIONS *)

let getAlphabet fa =
	let symbols = getSymbols fa.transitions in
	canonical symbols
;;

let getStates fa =
	let states = getStateList fa.transitions in
	canonical states
;;

let gcut s ts =
	List.partition (fun(x,_,_) -> x = s) ts
;;

let determinism fa =
	existTransitions fa.transitions
;;

let reachable fa =	(* assumes fa is deterministic *)
	let reached = reaches fa.initialState fa.transitions in
	canonical (reached)
;;

let productive fa =
	let prod = efficient fa.transitions fa.acceptStates in
    canonical prod
;;

let accept w fa =
  checkWord w fa fa.initialState
;;

let generate n fa =
		let wordsIntoAlph = List.map (fun(x) -> [x]) (getAlphabet fa) in
		let words = generateAux wordsIntoAlph (getAlphabet fa) n in
		canonical (List.filter (fun(x) -> (accept2Aux x fa fa.transitions)) words)
;;

let accept2 w fa =
	accept2Aux w fa fa.transitions
;;



