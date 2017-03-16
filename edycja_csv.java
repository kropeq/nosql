import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

import java.io.*;

public class edycja_csv {
	
	// Funkcja glowna uruchamiajaca program
	public static void main(String[] args) throws IOException {
		
		edycja_csv obj = new edycja_csv();
		
		int opcja=-1;
		Scanner in = new Scanner(System.in);
		while(opcja != 0 && opcja!=1 ){
			System.out.println("Oczekiwany plik: training.1600000.processed.noemoticon.csv");
			System.out.println("Plik zwracany: tweets.csv \n");
			System.out.println("Wybierz jedn¹ z dwóch opcji: ");
			System.out.println("0 - Zakoñcz");
			System.out.println("1 - Przygotuj dane do importu");
			opcja = in.nextInt();
			switch(opcja){
			case 0: {
				System.exit(1); 
				break;
			}
			case 1: {
				obj.edytuj();
				break;
			}
			default: System.out.println("Z³y wybór, powtórz. \n");
				break;
			}
		}
		in.close();
	}
	
	// Funkcja przeksztalcajaca csv do oczekiwanej postaci
	public void edytuj() throws IOException{
		// Deklaracja zmiennych
		String line = "";
		File save_it = new File ("tweets.csv");
		PrintWriter out = new PrintWriter(save_it);
		BufferedReader br = new BufferedReader(new FileReader("training.1600000.processed.noemoticon.csv"));
		ArrayList<String> lista = new ArrayList<String>();
		Random randomGenerator = new Random();
		randomGenerator.setSeed(100);
		String podzial[], wylosowana, dl_geo, szer_geo;
		int dlugosc_linii = 0, index = 0;
		
		// Tworze liste (szerokosc,dlugosc) miast USA do losowania
		lista.add("40.7142700,-74.0059700"); // New York
		lista.add("32.7830600,-96.8066700"); // Dallas
		lista.add("37.7749300,-122.4194200"); // San Francisco
		lista.add("41.8500300,-87.6500500"); // Chicago
		lista.add("34.0522300,-118.2436800"); // Los Angeles
		lista.add("42.3314300,-83.0457500"); // Detroit
		lista.add("47.6062100,-122.3320700"); // Seattle
		lista.add("42.3584300,-71.0597700"); // Boston
		lista.add("39.2903800,-76.6121900"); // Baltimore
		lista.add("38.8951100,-77.0363700"); // Washington
		lista.add("39.9523300,-75.1637900"); // Philadephia
		lista.add("37.3393900,-121.8949600"); // San Jose
		lista.add("29.9546500,-90.0750700"); // New Orleans
		lista.add("29.7632800,-95.3632700"); // Houston
		lista.add("32.7153300,-117.1572600"); // San Diego
		lista.add("33.4483800,-112.0740400"); // Phoenix
		lista.add("41.4995000,-81.6954100"); // Cleveland
		lista.add("39.7391500,-104.9847000"); // Denver
		lista.add("29.4241200,-98.4936300"); // San Antonio
		lista.add("36.1749700,-115.1372200"); // Las Vegas
		lista.add("39.7683800,-86.1580400"); // Indianapolis
		lista.add("30.2671500,-97.7430600"); // Austin
		lista.add("25.7742700,-80.1936600"); // Miami
		lista.add("37.8043700,-122.2708000"); // Oakland
		lista.add("41.3081500,-72.9281600"); // New Haven
		lista.add("32.7765700,-79.9309200"); // Charleston
		lista.add("40.7607800,-111.8910500"); // Salt Lake City
		lista.add("28.5383400,-81.3792400"); // Orlando
		lista.add("33.5206600,-86.8024900"); // Birmingham
		lista.add("35.4675600,-97.5164300"); // Oklahoma City
		lista.add("45.5234500,-122.6762100"); // Portland
				
		out.println("\"Id\",\"CreationDate\",\"Username\",\"Tweet\",\"Latitude\",\"Longitude\"");
		while ((line = br.readLine()) != null) {
			// Losuje wspolrzedne geograficzne
			index = randomGenerator.nextInt(lista.size());
			wylosowana = lista.get(index);
			
			// Dziele wylosowana na szerokosc i dlugosc geo
			podzial = wylosowana.split(",");
			szer_geo = podzial[0];
			dl_geo = podzial[1];
			
			// Usuwam pierwsza kolumne ze zbednymi danymi
			dlugosc_linii = line.length();
			line = line.substring(4,dlugosc_linii);
			
			// Usuwam sredniki, ktore przeszkadzaja w zapisie csv
			if(line.indexOf(";") != -1 ){
				line = line.replace(";", "");
			}
			
			// Usuwam zbedna kolumne
			if(line.indexOf("\"NO_QUERY\",") != -1 ){
				line = line.replace("\"NO_QUERY\",", "");
			}
			
			// Dodaje do rekordow wylosowana dlugosc i szerokosc geograficzna
			line = line + ",\""+szer_geo+"\",\""+dl_geo+"\"";
			
			// Zapisujemy kolejne rekordy
			out.println(line);
		}
		out.close();
		br.close();
		System.exit(1); 
	}
	
}



















