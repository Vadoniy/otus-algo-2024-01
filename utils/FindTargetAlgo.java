package utils;

public abstract class FindTargetAlgo {
    protected final int ALPHABET_SIZE = 256;
    protected int cmp;

    public FindTargetAlgo() {
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

        return findTarget(text, targetText);
    }

    public abstract boolean findTarget(String text, String targetText);

    public int getCmp() {
        return cmp;
    }
}
