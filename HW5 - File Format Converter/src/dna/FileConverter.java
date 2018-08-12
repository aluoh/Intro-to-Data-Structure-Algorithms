package dna;

import java.io.*;
import java.util.*;


//
// Writes a fasta file consisting of conversion of all records from the fastq with
// sufficient quality and unique defline.
//

public class FileConverter 
{
	private File fastq;
	private File fasta;
	
	public FileConverter(File fastq, File fasta) // Constructs a FileConverter object
	{
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	public void convert() throws IOException // Converts file types from Fastq to Fasta
	{
		// Build chain of readers.
		FileReader fr = new FileReader(fastq);
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);

		// Build chain of writers.
		FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		
		// Read, translate, write.
		boolean done = false;
		while (!done)
		{
			try
			{
				FastqRecord fq = fqr.readRecord(); 
				if(fq == null) // Checks if it's end of FastqRecord
				{
					done = true;
					break;
				}
				else
				{
					if(!fq.qualityIsLow()) // If the quality isn't low, write it over to the fasta file
					{
						FastaRecord f = new FastaRecord(fq);
						faw.writeRecord(f);
					}
				}
			}
			catch (RecordFormatException message) // Incase of any invalid format
			{
				System.out.println(message.getMessage());
			}
		}
		pw.close(); // Closes the writers/readers
		fw.close();
		br.close();
		fr.close();
	}
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
