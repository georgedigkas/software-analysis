/**
 * 
 */
package com.digkas.softwareanalysis;

import com.github.gumtreediff.tree.ITree;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
public class Segment {

	private static final String NEW_LINE = "\r\n|\r|\n";

	private int startSegment;
	private int endSegment;
	private int startLine;
	private int endLine;

	public Segment(ITree t, String dstDiff) {
		String startSubstring = dstDiff.substring(0, t.getPos());
		String endSubstring = dstDiff.substring(0, t.getEndPos());

		this.startSegment = startSubstring.lastIndexOf(";") + 1;
		this.endSegment = dstDiff.indexOf(";", t.getEndPos() - 1) + t.getEndPos();

		this.startLine = startSubstring.split(NEW_LINE).length;
		this.endLine = endSubstring.split(NEW_LINE).length;
		//System.out.println("t.getPos():" + t.getPos() + "->" + dstDiff.substring(0, t.getPos()).lastIndexOf(";") + "\tt.getEndPos():" + t.getEndPos() + "->" + (dstDiff.substring(t.getEndPos(), dstDiff.length()).indexOf(";") + t.getEndPos()));
	}

	public int getStartSegment() {
		return startSegment;
	}

	public int getEndSegment() {
		return endSegment;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	@Override
	public String toString() {
		return "Segment [startSegment=" + startSegment + ", endSegment=" + endSegment + ", startLine=" + startLine
				+ ", endLine=" + endLine + "]";
	}

}
