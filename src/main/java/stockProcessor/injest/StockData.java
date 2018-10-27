package stockProcessor.injest;

public class StockData {

    private Quote lastQuote;
    private Quote previousQuote;

    public Quote getLastQuote() {
        return lastQuote;
    }

    public void setLastQuote(Quote lastQuote) {
        this.lastQuote = lastQuote;
    }

    public Quote getPreviousQuote() {
        return previousQuote;
    }

    public void setPreviousQuote(Quote previousQuote) {
        this.previousQuote = previousQuote;
    }
}
