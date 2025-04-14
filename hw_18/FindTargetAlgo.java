package hw_18;

public abstract class FindTargetAlgo {
    protected final int ALPHABET_SIZE = 256;
    protected int cmp;

    protected FindTargetAlgo() {
        this.cmp = 0;
    }

    public boolean check(String text, String targetText) {
        if ((targetText == null || text == null)
                || (targetText.length() > text.length())) {
            return false;
        }

        if (targetText.isEmpty()) {
            return true;
        }

        return check(text, targetText);
    }

    protected abstract boolean findTarget(String text, String targetText);

    public int getCmp() {
        return cmp;
    }
}
