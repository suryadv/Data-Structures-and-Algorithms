/*Programming 3-LCS
//Surya Kiran Divakaruni Venkata

README:

->The operating system used for coding is Windows 10 and the language used is Java. The IDE used is Eclipse.
->There are two ways to generate new DNA strands for input, either use "LCS filename.txt" or else generate a new input using the "new" command.
->The "new" command is structured to generate random input DNA sequences that are greater than 6 in length and lesser than or equal to 20 in size.
->The functionality of the main commands of the code are as below:
1) "LCS" is used to find from all of the input DNA strands stored in the program the LCS.
2) "Print" is used for printing all of the input DNA strands as well as their LCS.
3) "Quit" is used to terminate the program.


Below is the flow of the code:
->For finding the LCS
--First a matrix "c" is created for storing the computed values as well as the matrix direction.
--The value of the first row and column are initialized as zero.
--Now, we iterate through the matrix
	-If we find characters that are matching: 
		i) assign the value of c[i][j] as the diagonal element + 1
		ii) after that we assign the direction as "cross"
	-Else if we are not able to locate characters that match
		i) we choose the maximum value between topmost and left element and assign it to c[i][j]
		ii) after that we assign the corresponding direction as "top" or "left"
--For printing the subsequence
	-We start the process from the last element (last row, last column)
	-If the direction designated is "cross", then append the character to the string and move diagonally.
	-else if direction is "top", then move towards top
	-else if direction is "left", then move towards left
--After the end of while loop, reverse the subsequence string and add it to the output Arraylist
Summary of what works- The code is able to take input file. When there is input of DNA strands it calculates LCS.
When input file is empty it shows its empty. Its able to eliminate blank lines. The output displays the LCS and its length for DNA strands.
The code works for multiple inputs and in each case calculates its LCS and length along with running time.
Summary of what doesn't work- The code for running time shows a very minute value. Not sure what the expected run time should be. Since 
in given example in canvas it shows 10 seconds. But in my code it shows 1ms or in milliseconds range.
Data structure design- The output is stored as an arraylist. We make uses of matrices for calculating LCS.
*/

import java.io.*;
import java.util.*;

public class LCSMenu {
	static ArrayList<String> output = new ArrayList<>();
	static long endTime = 0;

	public static void main(String[] args) {
		FileInputStream fileinput; // Declaration to read input file
							
		BufferedInputStream bufferinput = null;
		String s = new String();
		String[] list1;
		ArrayList<String> list = new ArrayList<>(); // Arraylist used to store strands
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nPlease provide a command \n");
			String selection = scanner.nextLine(); // Command provided in console
													// is read
			if (selection.startsWith("LCS ")) {              // If command starts with LCS , this loop works
				StringBuilder s1 = new StringBuilder();
				try {
					fileinput = new FileInputStream(selection.substring(4, selection.length())); // a
			//FileInputstream object is created on file name passed in arguments.																					// FileInputstream
																								
					bufferinput = new BufferedInputStream(fileinput); // Creates
																		// a
																		// BufferInputStream
																		// from
																		// the
																		// FileInputStream
					/*
					 * iterates through bufferinput and appends to string
					 * builder
					 */
					while (bufferinput.available() > 0) {
						char ch = (char) bufferinput.read();
						s1.append(ch);
					}
					/* convert to string */
					s = s1.toString();
					if (s.isEmpty()) {
						System.out.println("File provided is empty"); // prints file is empty if file is empty.
																	
					} else {
						list1 = new String[s.split("\n").length];
						list1 = (s.split("\n")); // splits the file entries
													// using new lines.
						if (list1.length != 0) {
							for (String temp : list1) {
								// Rows will be discarded if the file contains any blank
								if (temp.trim().equals("")) {
								} else {
									list.add(temp.trim()); // Every entry is added.
															//Read from file to arraylist.
										
								}

							}
							if ((list.size() % 2) != 0) {
								list.remove(list.size() - 1); // if file
																// contains odd
																// no of DNA
																// strands, will
																// delete the
																// last entry to
																// make even no
																// of pairs
							}
							System.out.println("strands stored successfully.");
						} else {
							System.out.println("File provided is empty");

						}

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("File not found");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (selection.equalsIgnoreCase("new")) {
				Random r = new Random(); // Block used to create random values.

				String alphabet = "ACGT";

				int count = 0;
				for (int i = 0; count < 2; i++) {
					if (count < 2) {
						int k = r.nextInt(21);
						if (k > 6) { // Restricts length from 7 to 20
							count++;
							StringBuffer sb = new StringBuffer();
							// generate random characters
							for (int j = 0; j < k; j++) {

								sb.append(alphabet.charAt(r.nextInt(alphabet.length()))); 
								//Randomly generated characters are appended.
							}
							list.add(sb.toString()); // add the newly created
														// DNA strands to input
														// array list
						}
					}

				}
				System.out.println("strands generated successfully:"); 
				// prints if strands are generated successfully.
				System.out.println(list.get(list.size() - 2));
				System.out.println(list.get(list.size() - 1));

			}

			else if (selection.equalsIgnoreCase("LCS")) {
				long startTime = System.currentTimeMillis();         // Start clock is declared.
				int counter = output.size();
				// Picks two strings from input array list and then compute LCS
				
				if (list.size() != 0 && (counter * 2) != list.size()) {
					for (int k = counter * 2; k < list.size(); k = k + 2) {
						char X[] = list.get(k).toCharArray();
						char Y[] = list.get(k + 1).toCharArray();
						LCS(X, Y); // When LCS function is called LCS is computed.
					}
					System.out.println(startTime);                 // prints start time.
					System.out.println(System.currentTimeMillis());
					endTime = System.currentTimeMillis() - startTime;   // Running time is calculated.
					System.out.println(endTime);
					System.out.println(
							"longest common subsequences (LCS) of all stored pair strands computed successfully. ");
				} else {
					System.out.println("No DNA strands available to compute LCS");
				}

			} else if (selection.equalsIgnoreCase("print")) {

				if (list.size() != 0 && output.size() != 0) {
					System.out.println("-----------------------------------------------------");
					for (int l = 0; l < list.size(); l = l + 2) {
						if (output.size() > l / 2) {
							System.out.println("The DNA strands: ");
							System.out.println(list.get(l));
							System.out.println(list.get(l + 1));
							System.out.println("LCS is " + output.get(l / 2)); // prints
																				// output
																				// corresponding
																				// to
																				// 2
																				// input
																				// DNA
																				// strands
							System.out.println("LCS length is " + output.get(l / 2).length());							
							System.out.println("-----------------------------------------------------");
						}

					}
					
					System.out.println("Running time: "+endTime+" milliseconds");
				} else {
					System.out.println("No computed LCS available to print");
				}

			} else if (selection.equals("quit")) {
				// finish the program
				break;
			} else {
				System.out.println("Please give a valid command");
			}
		}

	}

	static void LCS(char X[], char Y[]) {
		int m = X.length;
		int n = Y.length;
		int c[][] = new int[m + 1][n + 1]; // matrix is created
		String direction[][] = new String[m + 1][n + 1]; // store direction of matrix
		for (int i = 1; i <= m; i++) // Initializing first column as zero
			c[i][0] = 0;
		for (int j = 0; j <= n; j++) // Initializing first row as zero
			c[0][j] = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++)
				if (X[i - 1] == Y[j - 1]) { // If the characters match
					c[i][j] = c[i - 1][j - 1] + 1;
					direction[i][j] = "cross";
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					// If the characters don't match and top value of matrix c
					// is greater than or equal to the left value
					c[i][j] = c[i - 1][j];
					direction[i][j] = "top";
				} else {
					// Left value of matrix c is greater than the top value
					c[i][j] = c[i][j - 1];
					direction[i][j] = "left";
				}
		}
		StringBuilder str = new StringBuilder();
		int i = m;
		int j = n;
		while (i != 0 && j != 0) {
			// While first row or first column is not reached
			if (direction[i][j] == "cross") {
				str = str.append(X[i - 1]); // Appending matching character
				// Moving cross
				i--;
				j--;
			} else if (direction[i][j] == "top") {
				i--; // Moving up
			} else if (direction[i][j] == "left") {
				j--; // Moving left
			}

		}
		output.add(str.reverse().toString());
		// Reverse then add

	}

}