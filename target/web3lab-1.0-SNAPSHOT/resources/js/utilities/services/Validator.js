export default class Validator {
    static regex = /^-?\d+(\.\d+)?$/;

    static isValid(x, y, r) {
        return this.isValidX(x) && this.isValidY(y) && this.isValidR(r)
    }

    static isValidR(val) {
        if (val.length !== 0) {
            return true;
        }
        if (Number(val) < 2 || Number(val) > 5) {
            return false;
        }
        return Validator.regex.test(val);
    }

    static isValidX(val) {
        if (Number(val) === undefined) {
            return false;
        }
        if (Number(val) === 0) {
            return true;
        }

        if (!Number(val)) {
            return false;
        }
        if (Number(val) < -5 || Number(val) > 5) {
            return false;
        }
        return Validator.regex.test(val);

    }

    static isValidY(val) {
        if (Number(val) === 0) {
            return true
        }
        if (!Number(val)) {
            return false
        }
        if (Number(val) < -3 || Number(val) > 3) {
            return false;
        }
        return Validator.regex.test(val);
    }
}
