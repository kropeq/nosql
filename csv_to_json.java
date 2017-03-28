import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class csv_to_json {
	
	// Funkcja glowna uruchamiajaca program
	public static void main(String[] args) throws IOException {
		
		// Deklaracja zmiennych
		String line = "";
		File save_it = new File ("tweets_small.json");
		PrintWriter out = new PrintWriter(save_it);
		BufferedReader br;
		if(args.length == 0){
			br = new BufferedReader(new FileReader("training.1600000.processed.noemoticon.csv"));
		} else {
			br = new BufferedReader(new FileReader(args[0]));
		}
		
		ArrayList<String> lista = new ArrayList<String>();
		Random randomGenerator = new Random();
		randomGenerator.setSeed(100);
		String podzial[], podzial_wspol[],wylosowana;
		int index = 0;
		
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
		
		String outJSON = "";
		int dlugosc = 0;
		int id = 1;
		// zamieniamy csv na json
		while ((line = br.readLine()) != null) {
			
			out.println("{ \"index\" : { \"_index\" : \"tweets\", \"_type\" : \"tweet\", \"_id\" : \""+ id +"\" } }");
			// Losuje wspolrzedne geograficzne
			index = randomGenerator.nextInt(lista.size());
			wylosowana = lista.get(index);
			
			// Dziele wylosowana na szerokosc i dlugosc geo
			podzial_wspol = wylosowana.split(",");
		
			// Usuwam zbedna kolumne
			if(line.indexOf("\"NO_QUERY\",") != -1 ){
				line = line.replace("\"NO_QUERY\",", "");
			}
			outJSON = "{ ";
			
			// dzielimy rekordy na kolumny
			podzial = line.split(",");
			dlugosc = podzial.length;
			
			outJSON += "\"Rating\" : "+ podzial[0] + ",";
			outJSON += "\"id\" : " + podzial[1] + ",";
			outJSON += "\"CreationData\" : " + podzial[2] + ",";
			outJSON += "\"Username\" : " + podzial[3] + ",";
			
			// Poniewaz tweet moze zawierac przecinki, to split 
			// podzieli nam rekordy na wiecej niz ilosc kolumn
			// trzeba wiec scalic te, ktore sa oddzielone przecinkami z tweeta
			outJSON += "\"Tweet\" : ";
			for(int i=4; i<dlugosc;i++){
				if(i==4){
					outJSON += podzial[i];
				} else {
					outJSON += ","+podzial[i];
				}
				if(i==dlugosc-1){
					outJSON += ",";
				}
			}
			
			outJSON += "\"Location\" : [" + podzial_wspol[1] + "," + podzial_wspol[0]+ "]";
			outJSON += "}";
			
			out.println(outJSON);
			if(id==1000){
				break;
			}
			id++;
		}
		out.close();
		br.close();
		System.exit(1); 
	}	
	
}



















