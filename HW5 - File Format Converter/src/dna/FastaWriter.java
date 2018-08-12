package dna;
import java.io.*;

//
// Writes a fasta record to a print writer.
//

public class FastaWriter 
{
	
	private PrintWriter tPw;

	public FastaWriter(PrintWriter pw)	// Constructs a FastaWriter by initializing the PrintWriter
	{
		tPw = pw;
	}

	public void writeRecord(FastaRecord rec) throws IOException // Writes the FastaRecord
	{
		String defline = rec.getDefline(); // Gets the defline from FastaRecord
		String sequence = rec.getSequence(); // Gets the sequence from FastaRecord
		tPw.println(defline); // Uses the PrintWriter to write the defline & sequence
		tPw.println(sequence);
	}
}
