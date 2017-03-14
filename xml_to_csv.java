import java.util.Scanner;
import java.io.*;

public class xml_to_csv {
	
	// Funkcja glowna uruchamiajaca program
	public static void main(String[] args) throws IOException {

		xml_to_csv obj = new xml_to_csv();
		
		int opcja=-1;
		Scanner in = new Scanner(System.in);
		while(opcja != 0 && opcja!=1){
			System.out.println("Wybierz jedn¹ z dwóch opcji: ");
			System.out.println("0 - Zakoñcz");
			System.out.println("1 - Wykonaj konwersje");
			opcja = in.nextInt();
			switch(opcja){
			case 0: {
				System.exit(1); 
				break;
			}
			case 1: {
				obj.convert_xml();
				break;
			}
			default: System.out.println("Z³y wybór, powtórz. \n");
				break;
			}
		}
		in.close();
	}
	

	// Funkcja konwertujaca do xml
	public void convert_xml() throws IOException{		
		String line = "";
		File save_it = new File ("posts.csv");
		PrintWriter out = new PrintWriter(save_it);
		BufferedReader br = new BufferedReader(new FileReader("posts.xml"));
		
		// wyszukiwanie poszczegolnych tresci
		int poczatek,koniec;
		String tresc,id="",title="N/A",creationdate="",body="",tags="N/A",viewcount="0",answercount="0",commentcount="0";
		out.println("Id;ViewCount;CreationDate;Body;Title;Tags;AnswerCount;CommentCount");
		while ((line = br.readLine()) != null) {
			
			// jesli jest to rzad danych to sprawdzaj
			if(line.indexOf("<row Id=") != -1 ){
				
				// Id
				poczatek = line.indexOf("Id=");
				koniec = line.indexOf("PostTypeId=");
				
				// Sprawdzenie czy istnieja
				if(poczatek != -1 && koniec != -1 ){
					// wyciecie interesujacej nas czesci i usuniecie smieci
					id = line.substring(poczatek+4,koniec-2);
				}
				
				// CreationDate
				poczatek = line.indexOf("CreationDate=");
				koniec = line.indexOf("Score=");
				if(poczatek != -1 && koniec != -1){
					creationdate = line.substring(poczatek+14,koniec-15);
				}			
				
				// ViewCount
				poczatek = line.indexOf("ViewCount=");
				koniec = line.indexOf("Body=");
				if(poczatek != -1 && koniec != -1 ){
					viewcount = line.substring(poczatek+11,koniec-2);
				}
				
				// Body
				poczatek = line.indexOf("Body=");
				koniec = line.indexOf("OwnerUserId=");
				if(poczatek != -1 && koniec != -1){
					body = line.substring(poczatek+6,koniec-2);
					body = body.replace("\"","");
					body = body.replace("&lt;","");
					body = body.replace("/p&gt;","");
					body = body.replace("/a&gt;","");
					body = body.replace("p&gt;","");
					body = body.replace("&gt;","");
					body = body.replace("&#xA;","");
					body = body.replace("&quot;"," ");
					body = body.replace("a href=","");
					body = body.replace("rel= nofollow","");
					body = body.replace("&amp;","");
					body = body.replace("amp;","");
					body = body.replace(";","");
				}
				
				// Title
				poczatek = line.indexOf("Title=");
				koniec = line.indexOf("Tags=");
				if(poczatek != -1 && koniec != -1){
					title = line.substring(poczatek+7,koniec-2);
					title = title.replace("&lt;","");
					title = title.replace("/p&gt;","");
					title = title.replace("/a&gt;","");
					title = title.replace("p&gt;","");
					title = title.replace("&gt;","");
					title = title.replace("&#xA;","");
					title = title.replace("&quot;"," ");
					title = title.replace("a href=","");
					title = title.replace("rel= nofollow","");
					title = title.replace("&amp;","");
					title = title.replace("amp;","");
					title = title.replace(";","");
					title = title.replace("\"","");
				}
				
				// Tags
				poczatek = line.indexOf("Tags=");
				koniec = line.indexOf("AnswerCount=");
				if(poczatek != -1 && koniec != -1){
					tags = line.substring(poczatek+6,koniec-3);
					tags = tags.replace("&lt;","");
					tags = tags.replace("&gt","");
					tags = tags.replace(";",",");
				}
				
				// AnswerCount
				poczatek = line.indexOf("AnswerCount=");
				koniec = line.indexOf("CommentCount=");
				if(poczatek != -1 && koniec != -1 ){
					answercount = line.substring(poczatek+13,koniec-2);
				}
				
				// CommentCount
				poczatek = line.indexOf("CommentCount=");
				koniec = line.indexOf("FavoriteCount=");
				if(poczatek != -1 && koniec != -1 ){
					commentcount = line.substring(poczatek+14,koniec-2);
				}
				
				// Z³¹czenie i zapis
				tresc = id + ";" + viewcount + ";" + creationdate + ";" + body + ";" + title + ";" + tags + ";" + answercount + ";" + commentcount;
				out.println(tresc);
				title="N/A";
				tags="N/A";
				answercount="0";
				commentcount="0";
				viewcount="0";
			}
			
		}
		out.close();
		br.close();
		System.exit(1); 
	}
	
}
