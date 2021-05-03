import java.io.* ;
public class CopyFile
{
	public static void main(String[] args)
	{
		if(args.length == 1 || args.length == 2)
		{
			File src = new File(args[0]) ;//Initializing Source Path with first command line arguement
			File dest = new File("") ;//Initializing Destination Path at current Location of this program 
			if(args.length == 2)//Checking presence of second command line arguement 
			{
				dest = new File(args[1]) ;//Initializing Destination Path from second command line arguement 
			}
			else
			{
				dest = new File(dest.getAbsolutePath()) ;//Initializing Destination Path to current directory with Full Path. 
				//This statement is only for proper program functioning
				//This is not an optional statement 
				//Removing this statement results not copying same file in same directory
				//As Destination Path show conflict during execution by doing so  
			}
			if(src.exists())//Check for Source File Existence
			{
				if(src.isDirectory())//Check whether Source file is a folder or not
				{
					//Source is a Folder
					if(dest.isDirectory() && dest.exists())//Check whether Destination file is a folder and its existence 
					{
						//Operations Done if Destination exists and its a folder
						System.out.println("Source : "+src+"\nDestination : "+dest+"\nCopying...") ;//Printing Source and Destination location
						String newDestinationName = dest+"/"+src.getName() ;
						boolean checkForDest = new File(newDestinationName).mkdir() ;//Creating Source Folder at Destination Location for Copying 
						if(checkForDest)//Check whether folder is created or not
						{
							//Copying Folder Initiated
							copyFolder(src,new File(newDestinationName)) ;
						}
						else
						{
							//Destination location already exists
							//Renaming Folder Name adding "(num)" at the end, num starts from 0 till it does not create a unique name 
							//Chance of Infinite loop   
							System.out.println("Destination Already Exist\nRenaming Files...") ;
							int i = 0 ;
							while(true)
							{
								checkForDest = new File(newDestinationName+"("+Integer.toString(i)+")").mkdir() ;
								if(checkForDest)
								{
									copyFolder(src,new File(newDestinationName+"("+Integer.toString(i)+")")) ;
									break ;
								}
								i++ ;
							}
							
						}
						System.out.println("Done...") ;//Copying Done 
					}
					else
					{
						System.out.println("Destination is a File or doesn't exists !!!") ;//Copying Failed
					}
				}
				else if(src.isFile())
				{
					//Source is a File
					if(dest.isDirectory() && dest.exists())//Check whether Destination file is a folder and its existence
					{
						//Operations Done if Destination exists and its a folder
						System.out.println("Source : "+src+"\nDestination : "+dest+"\nCopying...") ;//Printing Source and Destination location
						try
						{
							//Copying File Initiated
							boolean checkForDest = new File(dest.getAbsolutePath()+"/"+src.getName()).exists() ;
							if(checkForDest)
							{
								//Destination File already exists
								//Renaming File Name adding "(num)" at the end, num starts from 0 till it does not create a unique name 
								//Chance of Infinite loop   
								System.out.println("Destination Already Exist\nRenaming Files...") ;
								String destinationFilename = dest.getAbsolutePath()+"/"+filenameWithoutFileExtension(src.getName()) ;//Getting only source filename without its extension
								String destinationFileExtension = fileExtension(src.getName()) ;//Getting source file extension only 
								int i = 0 ;
								while(true)
								{
									checkForDest = new File(destinationFilename+"("+Integer.toString(i)+")"+destinationFileExtension).exists() ;
									if(!checkForDest)
									{
										copyFile(src,new File(destinationFilename+"("+Integer.toString(i)+")"+destinationFileExtension)) ;
										break ;
									}
									i++ ;
								}
							}
							else
							{	
								copyFile(src,new File(dest.getAbsolutePath()+"/"+src.getName())) ;
							}
						}
						catch(IOException e)
						{
							//Error Occurred during Copying
							System.out.println(e.getMessage()) ;
							System.out.print("Un") ;
						}
						System.out.println("Done...") ;//Copying Done
					}
					else
					{
						System.out.println("Destination is a File or doesn't exists !!!") ;//Copying Failed
					}
				}
				else
				{
					System.out.println("Source is neither File nor Directory !!!") ;//Source is Unidentified
				}
			}
			else
			{
				System.out.println("Source does not Exist !!!") ;//Source does not exist
			}
		}
		else
		{
			System.out.println("0 or more than 2 inputs are provided !!!") ;//Invalid Inputs
		}
	}
	static void copyFile(File src,File dest) throws IOException
	{
		InputStream is = null ;
		OutputStream os = null ;
		try
		{
			is = new FileInputStream(src) ;
		    os = new FileOutputStream(dest) ;
		    byte[] buffer = new byte[1024] ;
		    int length;
		    while((length = is.read(buffer)) > 0)
		    	os.write(buffer, 0, length) ;
		}
		finally
		{
			is.close() ;
	        os.close() ;
		}
	} 
	static void copyFolder(File src,File dest)
	{
		String names[] = src.list() ;
		for(String name : names)
		{
			File tmpPath = new File(src.getAbsolutePath()+"/"+name) ;
			if(tmpPath.isFile())
			{
				try
				{
					copyFile(new File(tmpPath.getAbsolutePath()),new File(dest.getAbsolutePath()+"/"+name)) ;
				}
				catch(IOException e)
				{
					System.out.println(e.getMessage()) ;
					System.out.print("Un") ;
				}
			}
			else
			{
				createDirectory(new File(dest.getAbsolutePath()+"/"+name)) ;
				copyFolder(new File(tmpPath.getAbsolutePath()),new File(dest.getAbsolutePath()+"/"+name)) ;
			}
		}
	}
	private static void createDirectory(File src)
	{
		if(!src.exists())
			src.mkdir() ;
	}
	private static String filenameWithoutFileExtension(String filename)
	{
		if(filename == null)
			return "" ;
		int pos = filename.lastIndexOf(".") ;
		if(pos == -1)
			return filename ;
		return filename.substring(0,pos) ;
	}
	private static String fileExtension(String filename)
	{
		if(filename == null)
			return "" ;
		int pos = filename.lastIndexOf(".") ;
		if(pos == filename.length()-1 || pos == -1)
			return "" ;
		return filename.substring(pos) ;
	}
}