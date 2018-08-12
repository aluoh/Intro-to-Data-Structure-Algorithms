package dna;


//
// FastaRecord contains the defline and sequence string
// from a record in a Fasta OR Fastq file.
//

public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence; 
	
	public FastaRecord(String defline, String sequence) throws RecordFormatException // Constructs a FastaRecord
	{
		if(defline.charAt(0) != '>') // Checks if first char starts with '>'
		{
			throw new RecordFormatException("Bad 1st char in FastaRecord, expected >"); // Throws exception if it doesn't
		}
		this.defline = defline;
		this.sequence = sequence;
	}
	
	public FastaRecord(FastqRecord fastqRec) // Initializes instance variables with FastqRecord values instead
	{
		this.defline = ">" + fastqRec.getDefline().substring(1); // Sets the defline = Fastq's defline by replacing @ to >
		this.sequence = fastqRec.getSequence(); // Sets the sequence = Fastq's sequence
	}
	
	//
	// Getter methods from the DNARecord interface
	//
	
	public String getDefline()
	{
		return defline;
	}
	
	public String getSequence()
	{
		return sequence;
	}
	
	
	public boolean equals(Object o) // Checks for deep equality between 2 FastaRecord Objects
	{
		FastaRecord that = (FastaRecord) o;
		if (!this.defline.equals(that.defline))
		{
			return false;
		}
		if (!this.sequence.equals(that.sequence))
		{
			return false;
		}
		return true;
	}
	
	public int hashCode() // Generates a hashCode for FastaRecord
	{
		return defline.hashCode() + sequence.hashCode();
	}
}
