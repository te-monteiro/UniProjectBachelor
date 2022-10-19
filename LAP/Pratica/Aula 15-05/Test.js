

class Succ {

    constructor(a0) {
        this.a0 = this.current = a0;

    }

    first() {
        this.current = this.a0;
        return this.a0;
    }

    current() {
        return this.current;
    }

    at(n) {
        this.first();
        for (var i = 0; i < n; i++)
            this.next();
        return this.current;
    }

    print(n) {
        console.log(this.first());
        for (var i = 0; i < n; i++) {
            console.log(this.next());
        }

    }

}


class Const extends Succ {

    constructor(ao) {
        super(a0);

    }

    next() {
        return this.current;
    }
}


class Arith extends Succ {

    constructor(ao, inc) {
        super(a0);
        this.inc = inc;

    }

    next() {
        return this.current += this.inc;
    }
}


class Geom extends Succ {

    constructor(ao, mult) {
        super(a0);
        this.mult = mult;

    }

    next() {
        return this.current *= this.mult;
    }
}

(new Geom(2, 3)).print(20);