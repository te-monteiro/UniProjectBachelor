(* FiniteAutomata module body *)

(* 
Aluno 1: 52770
Aluno 2: 53044

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
            ("AB",'a',"START"); ("AB",'b',"START"); ("AB",'c',"SUCCESS");
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


(* PUBLIC FUNCTIONS *)


let getStates (fa:fAutomaton) =
		let a =List.map (fun (s1,_,_) -> s1) fa.transitions in
		let b =List.map (fun (_,_,s2) -> s2) fa.transitions in
		canonical (a@b)		
;;

let getAlphabet (fa:fAutomaton) =
    canonical(List.map (fun (_,sy,_) -> sy) fa.transitions)
;;

let gcut (s:state) (ts:transitions) =
	List.partition(fun (s1,sy,s2) -> s = s1) ts
;;
(* como o gcut mas faz a particao apartir do estado final*)
let rec gCut2 (s:state) (ts:transitions)=
List.partition(fun (s1,sy,s2) -> s = s2) ts
;;

(*fazemos uma lista com os simbolos de um estado para o outro, depois*)
(* comparamos o tamanho desta lista com a sua canonica*)
(* se tiver tamanho diferente significa que a lista tinha repeticao*)
(* logo nao e determinista*)
let rec determinismRec list fa =
	match list with 
	| []-> true
	| x :: xs -> let (a,b) = gcut x fa.transitions in
							let l = List.map (fun (_,sy,_) -> sy) a in
							if ( List.length l!= List.length( canonical( l)) ) then false
							else determinismRec xs fa
;;

let determinism (fa:fAutomaton) =
		determinismRec (getStates fa) fa
;;

(*ls e uma lista de estados onde a funcao ja passou*)
(* dom e o conjunto de transicoes que faltam ser testadas*)
(*e e uma lista de estados*)
(* para cada estado de e verifica se esta contido em ls*)
(* se sim e porque ja passamos por este estado se nao temos de procurar*)
(* as transicoes que provem dele ( reachable2)*)
let rec findNext ( e : states) ( ls : states) (dom : transitions) =
	match e with
		| []-> e
		| x :: xs -> if( List.mem x ls) then findNext xs ls dom
								else  let ls2 = x:: ls in
											reachable2 x ls2 dom @ findNext xs ls dom

and
(* st e o estado atual*)
(* ls3 e uma lista de estados por onde a funcao ja passou*)
(* dom2 sao as transicoes que ainda nao foram testadas*)
(* faz o gcut do estado atual e retira todos os estados que ele pode originar*)
(* faz o teste se esses estados ja foram testados ou precisam de ser testados tb(findNext)*)
 reachable2 (st : state) ( ls3 : states) (dom2 : transitions)=
	match dom2 with
		|  [] -> [st]
		| _ -> let ( a , b ) = gcut st dom2 in
					let e1 = List.map (fun (_,_,s2)-> s2) a in 
				st::( e1 @  findNext e1 ls3 b)
;;
	
let reachable (fa:fAutomaton) =
    ((canonical( reachable2 fa.initialState [] fa.transitions)):states)
;;

let rec findBefore ( e : states) ( ls : states) (dom : transitions) =
	match e with
		| [] -> []
		|  (x:state) :: xs -> if( List.mem x ls) then findBefore xs ls dom
								else  
									let ls2 = x:: ls in
								 productiveRec x ls2 dom @ findBefore xs ls2 dom

and

 productiveRec (st : state) ( ls : states) (dom : transitions)=
	match dom with
		|  [] -> []
		| _ -> let ( a , b ) = gCut2 st dom in
					let e = List.map ( fun (s1,sy,s2) -> s1) a in
				st::(	e @ findBefore e ls  b) 
;;

let rec productiveL (ac : states) ( ls : states) (dom : transitions) =
	match ac with
		| []-> []
		| x :: xs -> productiveRec x ls dom @ productiveL xs ls dom
;;
(*raciocinio inverso ao reachable*)
let productive (fa: fAutomaton) =
	canonical(productiveL fa.acceptStates [] fa.transitions)
;;

let rec nextState (x:symbol) (st:state) (ts:transitions)=
	match ts with
	| []->""
	| (s1,sy,s2) :: xs -> if( s1 = st && sy = x ) then s2
												else nextState x st xs 
;;

let rec acceptH w	fa state =
	match w with
		| [] -> false
		| x :: xs -> let ns = nextState x state fa.transitions in
								if  List.exists ( fun s -> s =  ns) fa.acceptStates then true
								else acceptH xs fa ns
	;;

let rec accept w fa =
   acceptH w fa fa.initialState
;;
(* cria uma lista para cada letra do alfabeto*)
let rec basic sl =
	match sl with
		| [] -> []
		| x :: xs -> [x] :: basic xs
	;;
(* adiciona uma letra a cada palavra*)
let rec add sl1 b=
match b with
| [] -> []
| y1 :: ys1 -> add2 y1 sl1 @ add sl1 ys1
	
and
	add2 y sl2=
	match sl2 with
	| []-> []
	| x :: xs -> (x::y) :: add2  y xs 
;;
(*chama o add de acordo com o numero tamanho da palavra necessitada*)
let rec junc n sl b=
	if n<=0 then b
	else
	let b2 = add sl b in
	junc (n-1) sl b2
;;
(*cria uma lista com todos os estados seguintes possiveis*)
let rec acceptH2 st letter  ts =
	let (a,b) = gcut st ts in
	let l1 = List.filter(fun (s1,sy,s2)-> sy = letter) a in
	let l2 = List.map( fun( s1,sy,s2)-> s2) l1 in
	l2
;;

let rec ola w sti fa =
	match w with
	| []-> []
	| x :: xs -> match xs with
								|[] -> acceptH2 sti x fa.transitions
								| xs ->let nsl = acceptH2 sti x fa.transitions in	
								division nsl xs fa
														
and
(* para cada estado seguinte possivel aplica a palavra *)
	division nsl2 w2 fa2 =
		match nsl2 with
			| []->[]
			| x2 :: xs2 -> ola w2 x2 fa2 @ division xs2 w2 fa2

;;
(* verifica se existe um elemento em comum nas duas listas*)
let rec check l1 l2 =
	match l1 with
		| [] -> false
		| x :: xs -> if( List.mem x l2 ) then true
									else check xs l2
;;

let accept2 w fa =
    check ( ola w fa.initialState fa) fa.acceptStates
;;

let generate n fa =
		let bAlf = basic (getAlphabet fa) in
		let alf = getAlphabet fa in
    canonical(List.filter(fun s -> accept2 s fa)( junc (n-1) (alf) (bAlf)))
;;