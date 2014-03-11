package edu.lclark.logic;

/** A column in the Truth Table. */
public class TruthTableColumn {
    /** The label of this column. */
    private String label;

    /** The truth-values in the rows of this column. */
    private boolean[] rows;

    public TruthTableColumn(String label, boolean[] rows) {
        this.label = label;
        this.rows = rows;
    }

    public boolean getValue(int row) {
        return rows[row];
    }

    public void setValue(int row, boolean value) {
        rows[row] = value;
    }

    public String getLabel() {
        return label;
    }
    
    public int getNumRows() {
    	return rows.length;
    }
}