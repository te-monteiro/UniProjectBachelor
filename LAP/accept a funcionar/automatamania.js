/*
 * Automatamania
 * 

Aluno 1: ?number ?name <-- mandatory to fill
Aluno 2: ?number ?name <-- mandatory to fill

Comment:

?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????
?????????????????????????

01234567890123456789012345678901234567890123456789012345678901234567890123456789

INSIDE THIS FILE, YOU CAN CHANGE EVERYTHING YOU WANT!
/*

/*
DOCUMENTATION
 
HTML and DOM documentation:
	http://ctp.di.fct.unl.pt/miei/lap/teoricas/17.html
	https://www.w3schools.com/html/default.asp
	https://www.w3schools.com/js/js_htmldom.asp

CSS documentation:
	https://www.w3schools.com/css/default.asp

JavaScript introduction:
	http://ctp.di.fct.unl.pt/miei/lap/teoricas/18.html
	http://ctp.di.fct.unl.pt/miei/lap/teoricas/19.html
	https://www.w3schools.com/js/default.asp

Cytoscape.js documentation:
	http://js.cytoscape.org/
*/

/* UTILITY GLOBAL FUNCTIONS ------------------------------------------------- */

function change() {
	document.getElementById('o meu id').innerHTML = cyGraph.fa.getStates().length;
	document.getElementById('o seu id').innerHTML = cyGraph.fa.acceptStates.length;
	document.getElementById('o nosso id').innerHTML = this.getAlpha().length;
	document.getElementById('o vosso id').innerHTML = this.isDeterminism();

}


function getAlpha() {
	return canonical(cyGraph.fa.transitions.map(([_0, trans, _1]) => trans));
}

function stateAndSymbols() {
	return cyGraph.fa.transitions.map(([s, trans, _0]) => [s, trans]);

};
function noRepetition(l) {
	if (l.length == canonical(l).length) {
		return true;
	}

	return false;
};

function isDeterminism() {
	if (this.noRepetition(stateAndSymbols())) {
		return 'Sim';
	} else {
		return 'Nao';

	}
};

function generateX(n, s, ts, l) {
	if (n = 0) {
		if (belongs(s, l)) {
			return [[]];

		} else {
			return [];
		}
	} else {
		let [x, _0] = fa.gcut(s, ts);
		return flatMap(([_0, symb, s]) => addAll(symb, generateX(n - 1), x));

	}
}

function generate(n) {
	return canonical(this.generateX(n, fa.initialState, fa.transitions, fa.acceptStates));
}


function generate(n) {
	return canonical(this.generateX(n, fa.initialState, fa.transitions, fa.acceptStates));
}


function equals(a, b) {  // fairly general structural comparison
	if (a === b)
		return true;
	if (!(a instanceof Object) || !(b instanceof Object))
		return false;
	if (a.constructor !== b.constructor)
		return false;
	const aProps = Object.getOwnPropertyNames(a);
	const bProps = Object.getOwnPropertyNames(b);
	if (aProps.length != bProps.length)
		return false;
	for (var i = 0; i < aProps.length; i++) {
		const propName = aProps[i];
		if (!equals(a[propName], b[propName]))
			return false;
	}
	return true;
}

function belongs(v, arr) {
	return arr.some(x => equals(x, v));
}

function canonical(arr) {
	// This version do not sort, so that we can control the order of the
	// states of the finite automata in the screen presentation
	const res = [];
	arr.forEach(x => { if (!belongs(x, res)) res.push(x); });
	return res;
}

function canonicalPrimitive(arr) {
	// This version do not sort, so that we can control the order of the
	// states of the finite automata in the screen presentation
	return Array.from(new Set(arr));
}

function cons(v, arr) {
	return [v].concat(arr);
}

function consLast(v, arr) {
	return arr.concat(v);
}

function isEmpty(arr) {
	// Beware: arr == [] does not work
	// For example, note that [] == [] is false
	return arr.length == 0;
}

function intersection(arr1, arr2) {
	return arr1.filter(value => arr2.includes(value));
}

function addAll(v, arr) {
	return arr.map(l => cons(v, l));
}

function flatMap(f, arr) {
	return arr.map(f).reduce((acc, a) => consLast(acc, a), []);
}

function clone(obj) {
	return Object.assign({}, obj);
}


/* FINITE AUTOMATA ---------------------------------------------------------- */

class AbstractAutomaton {  // common features to FA, PDA, TM, etc.
	constructor() { }
}

class FiniteAutomaton extends AbstractAutomaton {
	constructor(fa) {
		super();
		this.initialState = fa.initialState;
		this.transitions = fa.transitions;
		this.acceptStates = fa.acceptStates;
	}


	getStates() {
		return canonical(
			[this.initialState].
				concat(this.transitions.map(([s, _0, _1]) => s)).
				concat(this.transitions.map(([_0, _1, s]) => s)).
				concat(this.acceptStates)
		);
	}

	gcut(s, ts) {
		return [ts.filter(([z, _0, _1]) => z == s),
		ts.filter(([z, _0, _1]) => z != s)];
	}

	reachableX(s, ts) {
		const [x, xs] = this.gcut(s, ts);
		return cons(s, flatMap(([_0, _1, z]) => this.reachableX(z, xs), x));
	}

	reachable() {
		return canonical(this.reachableX(this.initialState, this.transitions));
	}

	productive() {
		const allStates = this.getStates();
		const reachAccepted =
			s => !isEmpty(intersection(this.acceptStates,
				this.reachableX(s, this.transitions)));
		return allStates.filter(reachAccepted);
	}

	transitionsFor(s, symb) {
		return this.transitions.filter(([s1, symb1, _]) =>
			s == s1 && symb == symb1);
	}

	acceptX(s, w) {
		if (isEmpty(w))
			return this.acceptStates.includes(s);
		else {
			const [x, ...xs] = w;
			const ts = this.transitionsFor(s, x);
			if (isEmpty(ts))
				return false;
			else {
				const [[_0, _1, s], ..._] = ts;
				return this.acceptX(s, xs);
			}
		}
	}

	accept(w) {
		return this.acceptX(this.initialState, w);
	}

	accept2X(s, w) {
		if (isEmpty(w)) {
			return this.acceptStates.includes(s);
		} else {
			const [x, ...xs] = w;
			const ts = this.transitionsFor(s, x);
			const [[_0, _1, t], ...d] = ts;
			return ts.some(([_0, _1, t]) => this.accept2X(t, xs));

		}
	}


	accept2(w) {
		return this.accept2X(this.initialState, w);
	}

}

const abc = new FiniteAutomaton({
	initialState: "START",
	transitions: [
		["START", 'a', "A"], ["START", 'b', "START"],
		["START", 'c', "START"], ["START", 'd', "START"],
		["A", 'a', "A"], ["A", 'b', "AB"], ["A", 'c', "START"], ["A", 'd', "START"],
		["AB", 'a', "A"], ["AB", 'b', "START"],
		["AB", 'c', "SUCCESS"], ["AB", 'd', "START"],
		["SUCCESS", 'a', "SUCCESS"], ["SUCCESS", 'b', "SUCCESS"],
		["SUCCESS", 'c', "SUCCESS"], ["SUCCESS", 'd', "SUCCESS"]
	],
	acceptStates: ["SUCCESS"]
});

function testAll() {
	//	getAlphabet
	console.log("");
	console.log(abc.getStates());
	console.log("");
	console.log(abc.gcut(abc.initialState, abc.transitions));
	console.log("");
	console.log(abc.reachable());
	console.log("");
	console.log(abc.productive());
	console.log("");
	console.log(abc.accept(['a', 'b', 'c']));
	console.log("");
	console.log(abc.accept(['a', 'b']));
	console.log(abc.generate(n));
	//	accept2
}

//Â testAll();


/* CYTOSCAPE GRAPHS AND USER INTERFACE -------------------------------------- */

// global constants and variables
var cyGraph;

const cyGraphStyle = {
	layout: {
		name: 'grid',
		rows: 2,
		cols: 2
	},

	style: [
		{
			selector: 'node[name]',
			style: {
				'content': 'data(name)'
			}
		},

		{
			selector: 'edge',
			style: {
				'curve-style': 'bezier',
				'target-arrow-shape': 'triangle',
				'label': 'data(symbol)'
			}
		},

		// some style for the extension

		{
			selector: '.eh-handle',
			style: {
				'background-color': 'red',
				'width': 12,
				'height': 12,
				'shape': 'ellipse',
				'overlay-opacity': 0,
				'border-width': 12, // makes the handle easier to hit
				'border-opacity': 0
			}
		},

		{
			selector: '.eh-hover',
			style: {
				'background-color': 'red'
			}
		},

		{
			selector: '.eh-source',
			style: {
				'border-width': 2,
				'border-color': 'red'
			}
		},

		{
			selector: '.eh-target',
			style: {
				'border-width': 2,
				'border-color': 'red'
			}
		},

		{
			selector: '.eh-preview, .eh-ghost-edge',
			style: {
				'background-color': 'red',
				'line-color': 'red',
				'target-arrow-color': 'red',
				'source-arrow-color': 'red'
			}
		},

		{
			selector: '.eh-ghost-edge.eh-preview-active',
			style: {
				'opacity': 0
			}
		}
	]
};

class CyGraph {
	constructor(nodes, edges, fa) {
		const spec = clone(cyGraphStyle);
		spec.elements = { "nodes": nodes, "edges": edges };
		spec.container = document.getElementById('cy');  // the graph is placed in the DIV 'cy'
		this.cy = cytoscape(spec);
		this.cy.$('#START').select();
		this.cy.on('select', function () {

			fa.getStates().forEach(function (element) {  // o array tem o nome dos estados , o element e uma string dinamica '#'
				cyGraph.cy.$('#' + element).removeStyle();  //cy representacao grafica do automato

			}
			);
		}
		);

		this.fa = fa;
	};



	static build(fa) {
		//alert("TO DO: convert the FA into the format required by Cytoscape (see sampleGraph)")
		const nodes = [];
		const edges = [];
		let listStates = fa.getStates();
		let listTransitions = fa.transitions;
		listStates.forEach(function (element) {
			nodes.push({ data: { id: element, name: element } });
		});
		listTransitions.forEach(function (trans) {
			edges.push({ data: { source: trans[0], symbol: trans[1], target: trans[2] } });
		}

		);

		return new CyGraph(nodes, edges, fa);
	}

	static load(text) {
		try {
			const json = JSON.parse(text);
			const fa = new FiniteAutomaton(json);


			return this.build(fa);
		} catch (ex) {
			alert(ex);
			document.getElementById('file-select').value = "";
		}
	}

	static sampleGraph() {
		return new CyGraph(
			[
				{ data: { id: "START", name: "START" } },
				{ data: { id: "A", name: "A" } },
				{ data: { id: "AB", name: "AB" } },
				{ data: { id: "SUCCESS", name: "SUCCESS" } }
			],
			[
				{ data: { source: "START", symbol: 'a', target: "A" } },
				{ data: { source: "START", symbol: 'b', target: "START" } },
				{ data: { source: "START", symbol: 'c', target: "START" } },
				{ data: { source: "START", symbol: 'd', target: "START" } },
				{ data: { source: "A", symbol: 'a', target: "A" } },
				{ data: { source: "A", symbol: 'b', target: "AB" } },
				{ data: { source: "A", symbol: 'c', target: "START" } },
				{ data: { source: "A", symbol: 'd', target: "START" } },
				{ data: { source: "AB", symbol: 'a', target: "A" } },
				{ data: { source: "AB", symbol: 'b', target: "START" } },
				{ data: { source: "AB", symbol: 'c', target: "SUCCESS" } },
				{ data: { source: "AB", symbol: 'd', target: "START" } },
				{ data: { source: "SUCCESS", symbol: 'a', target: "SUCCESS" } },
				{ data: { source: "SUCCESS", symbol: 'b', target: "SUCCESS" } },
				{ data: { source: "SUCCESS", symbol: 'c', target: "SUCCESS" } },
				{ data: { source: "SUCCESS", symbol: 'd', target: "SUCCESS" } }
			],
			abc
		);
	}
}




/* EVENT HANDLING ----------------------------------------------------------- */

function onLoadAction(event) {
	cyGraph = CyGraph.sampleGraph();
	change();
}




function getReachable(event) {
	let fa = cyGraph.fa;

	let seleciona = cyGraph.cy.$(':selected');  //o seleciona e um no

	let listaReachable = fa.reachableX(seleciona.id(), fa.transitions);
	console.log(listaReachable);

	console.log(fa.transitions);

	listaReachable.forEach(function (element) {  // o array tem o nome dos estados , o element e uma string dinamica '#'
		if (element != seleciona.id())
			cyGraph.cy.$('#' + element).style('background-color', 'pink');  //cy representacao grafica do automato


	}
	);

	alert("OP1 " + event);
}

function getProductive(event) {
	let fa = cyGraph.fa;

	let listaProductive = fa.productive();

	listaProductive.forEach(function (element) {
		cyGraph.cy.$('#' + element).style('background-color', 'yellow');
	}
	);
	alert("OP2 " + event);
}

function getUseful(event) {
	let fa = cyGraph.fa;

	let listaReachable = fa.reachable();

	listaReachable.forEach(function (element) {
		if (belongs(element, fa.productive())) {
			cyGraph.cy.$('#' + element).style('background-color', 'green');
		}
	})

	alert("OP3 " + event);
}


function getGenerate(n, event) {
	let fa = cyGraph.fa

	let listGenerate = fa.generate(n);
	console.log(listGenerate);

	let pai = getElementById('Generate');
	let newElement = document.createElement("SELECT");
	pai.appendChild(newElement);

	listGenerate.forEach(function (element) {

		document.createElement("OPTION").innerHTML = element;
		console.log(element);
	}
	);
}

function ana(form, event) {

	//	console.log(document.getElementById('inputBox').value);
	//	console.log(cyGraph.fa.accept(document.getElementById('inputBox').value));
	/*if (cyGraph.fa.accept(document.getElementById('inputBox').value)) {
		document.getElementById('text2').value = '1 Sim!';
	}*/

	if (this.isDeterminism() == 'Sim') {
		if (cyGraph.fa.accept(document.getElementById('inputBox').value)) {
			document.getElementById('text2').value = 'Sim!';
		}
		else {
			document.getElementById('text2').value = 'Nao!';

		}
	}
	else {
		if (cyGraph.fa.accept2(document.getElementById('inputBox').value)) {
			document.getElementById('text2').value = 'Sim!';

		}
		else {
			document.getElementById('text2').value = 'Nao!';

		}

	}

}

function fileSelectAction(event) {
	const file = event.target.files[0];
	if (file == undefined) // if canceled
		return;
	const reader = new FileReader();

	reader.onload = function (event) { cyGraph = CyGraph.load(event.target.result); change() };
	reader.readAsText(file);

}






