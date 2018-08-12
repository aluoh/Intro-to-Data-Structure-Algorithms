package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqRecord.
//


public class FastqReader 
{
	private BufferedReader tBr;
	
	public FastqReader(BufferedReader br) // Constructs a Fastq reader and initializes the buffered reader
	{
		tBr = br;
	}
	
	//
	// Reads the record and returns one if it is in a valid format
	//
	public FastqRecord readRecord() throws IOException, RecordFormatException 
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.
		String defline = tBr.readLine(); 
		if(defline == null) // Checks if defline exists, if not it's the end of file so it returns null
		{
			return null;
		}
		//
		// Read the next 3 lines from the buffered reader
		// and returns a FastqRecord if valid format
		// 
		else 
		{
			if(defline.charAt(0) != '@') // Checks first char to see if it starts with '@'
			{
				throw new RecordFormatException("Invalid 1st char in FastqRecord, saw " + defline.charAt(0) + " expected @");
			}
			String sequence = tBr.readLine(); // Reads 2nd line 'sequence'
			tBr.readLine(); // Reads the 3rd line '+' line
			String quality = tBr.readLine(); // Reads the 4th line 'quality'
			FastqRecord fq = new FastqRecord(defline, sequence, quality); // Constructs FastqRecord if valid format
			return fq; // Returns the FastaRecord object
		}
	}
}
