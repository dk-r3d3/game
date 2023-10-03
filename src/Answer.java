public class Answer {
    int bull;
    int cow;
    int maxTry;

    public Answer(int bull, int cow, int maxTry) {
        this.bull = bull;
        this.cow = cow;
        this.maxTry = maxTry;
    }

    @Override
    public String toString() {
        return "You have " + bull + " bulls " + "and " + cow + " cows. Your try count = " + maxTry;
    }
}
