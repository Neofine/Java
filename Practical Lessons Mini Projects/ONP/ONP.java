public class ONP {
    static class IllegalCharException extends Exception {
        public String toString() {
            return "Illegal character was written";
        }
    }

    static class IllegalComputationException extends Exception {
        public String toString() {
            return "There was division by 0";
        }
    }

    static class IllegalExpresionException extends Exception {
        public String toString() {
            return "Too much or too little of something";
        }
    }

    public static int compute(String expression) throws IStack.FullStackException, NumberFormatException, IStack.EmptyStackException, IllegalCharException, IllegalComputationException, IllegalExpresionException {
        Stack stack = new Stack();
        String in[] = expression.split(" ");
        for (String i: in) {
            try{
                int x = Integer.parseInt(i);
                stack.push(x);
            } catch(NumberFormatException e) {
                int first, second, outcome;
                first = stack.pop();
                second = stack.pop();
                outcome = operation(i, first, second);
                stack.push(outcome);
            }
        }
        int answer = 0;
        answer = stack.pop();
        if (stack.empty())
            return answer;
        else throw new IllegalExpresionException();
    }
    static int operation(String op, int a, int b) throws IllegalCharException, IllegalComputationException {
        int answer;
        if (op.charAt(0) == '+') {
            answer = b + a;
        }
        else if (op.charAt(0) == '-') {
            answer = b - a;
        }
        else if (op.charAt(0) == '*') {
            answer = b * a;
        }
        else if (op.charAt(0) == '/') {
            try {
                answer = b / a;
            } catch(ArithmeticException ae) {
                throw new IllegalComputationException();
            }
        }
        else if (op.charAt(0) == '%') {
            answer = b % a;
        }
        else if (op.charAt(0) == '^') {
            answer = (int) Math.pow(b, a);
        }
        else {
            throw new IllegalCharException();
        }
        return answer;
    }
}
