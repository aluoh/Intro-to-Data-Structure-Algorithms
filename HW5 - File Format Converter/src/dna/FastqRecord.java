package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//


public class FastqRecord implements DNARecord 
{
	private String defline;
	private String sequence;
	private String quality;
	
	// Constructs a Fastq record that throws a RecordFormatException
	
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if(defline.charAt(0) != '@') // Checks the first character to see if it starts with '@'
		{
			throw new RecordFormatException("Invalid 1st char in FastqRecord, saw " + defline.charAt(0) + " expected @");
		}
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;
	}
	
	
	// 
	// Methods from the interface
	//
	
	public String getDefline() // Gets the defline
	{
		return defline;
	}
	
	public String getSequence() // Gets the sequence
	{
		return sequence;
	}

	
	public boolean equals(Object o) // Checks for equality between 2 Fastq Records
	{
		FastqRecord that = (FastqRecord) o;
		if(!this.defline.equals(that.defline))
		{
			return false;
		}
		if(!this.sequence.equals(that.sequence))
		{
			return false;
		}
		if(!this.quality.equals(that.quality))
		{
			return false;
		}
		return true;
	}
	
	public boolean qualityIsLow() // Checks if the quality is low by seeing if the quality contains a ! or #
	{
		if(quality.contains("!") || quality.contains("#"))
		{
			return true;
		}
		return false;
	}
	
	public int hashCode() // Generates a hashcode for Fastq record
	{
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
	}	
}
