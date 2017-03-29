import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.*;

public class dane_do_geo {
	
	// Funkcja glowna uruchamiajaca program
	public static void main(String[] args) throws IOException {
		
		// Deklaracja zmiennych
		String line = "";
		File save_it = new File ("tweets_geo.json");
		PrintWriter out = new PrintWriter(save_it);
		BufferedReader br = new BufferedReader(new FileReader("training.1600000.processed.noemoticon.csv"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, String> map2 = new HashMap<String, String>();
		
		ArrayList<String> lista = new ArrayList<String>();
		Random randomGenerator = new Random();
		randomGenerator.setSeed(100);
		
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
		
		// Tworze mapê (wspolrzedne,ilosc_wystapien) do zliczania ilosci wystapien w bazie
		map.put("40.7142700,-74.0059700",0); // New York
		map.put("32.7830600,-96.8066700",0); // Dallas
		map.put("37.7749300,-122.4194200",0); // San Francisco
		map.put("41.8500300,-87.6500500",0); // Chicago
		map.put("34.0522300,-118.2436800",0); // Los Angeles
		map.put("42.3314300,-83.0457500",0); // Detroit
		map.put("47.6062100,-122.3320700",0); // Seattle
		map.put("42.3584300,-71.0597700",0); // Boston
		map.put("39.2903800,-76.6121900",0); // Baltimore
		map.put("38.8951100,-77.0363700",0); // Washington
		map.put("39.9523300,-75.1637900",0); // Philadephia
		map.put("37.3393900,-121.8949600",0); // San Jose
		map.put("29.9546500,-90.0750700",0); // New Orleans
		map.put("29.7632800,-95.3632700",0); // Houston
		map.put("32.7153300,-117.1572600",0); // San Diego
		map.put("33.4483800,-112.0740400",0); // Phoenix
		map.put("41.4995000,-81.6954100",0); // Cleveland
		map.put("39.7391500,-104.9847000",0); // Denver
		map.put("29.4241200,-98.4936300",0); // San Antonio
		map.put("36.1749700,-115.1372200",0); // Las Vegas
		map.put("39.7683800,-86.1580400",0); // Indianapolis
		map.put("30.2671500,-97.7430600",0); // Austin
		map.put("25.7742700,-80.1936600",0); // Miami
		map.put("37.8043700,-122.2708000",0); // Oakland
		map.put("41.3081500,-72.9281600",0); // New Haven
		map.put("32.7765700,-79.9309200",0); // Charleston
		map.put("40.7607800,-111.8910500",0); // Salt Lake City
		map.put("28.5383400,-81.3792400",0); // Orlando
		map.put("33.5206600,-86.8024900",0); // Birmingham
		map.put("35.4675600,-97.5164300",0); // Oklahoma City
		map.put("45.5234500,-122.6762100",0); // Portland
		
		// Tworze mapê (wspolrzedne,nazwa miasta) do opisu na mapie
		map2.put("40.7142700,-74.0059700","New York"); // New York
		map2.put("32.7830600,-96.8066700","Dallas"); // Dallas
		map2.put("37.7749300,-122.4194200","San Francisco"); // San Francisco
		map2.put("41.8500300,-87.6500500","Chicago"); // Chicago
		map2.put("34.0522300,-118.2436800","Los Angeles"); // Los Angeles
		map2.put("42.3314300,-83.0457500","Detroit"); // Detroit
		map2.put("47.6062100,-122.3320700","Seattle"); // Seattle
		map2.put("42.3584300,-71.0597700","Boston"); // Boston
		map2.put("39.2903800,-76.6121900","Baltimore"); // Baltimore
		map2.put("38.8951100,-77.0363700","Washington"); // Washington
		map2.put("39.9523300,-75.1637900","Philadelphia"); // Philadelphia
		map2.put("37.3393900,-121.8949600","San Jose"); // San Jose
		map2.put("29.9546500,-90.0750700","New Orleans"); // New Orleans
		map2.put("29.7632800,-95.3632700","Houston"); // Houston
		map2.put("32.7153300,-117.1572600","San Diego"); // San Diego
		map2.put("33.4483800,-112.0740400","Phoenix"); // Phoenix
		map2.put("41.4995000,-81.6954100","Cleveland"); // Cleveland
		map2.put("39.7391500,-104.9847000","Denver"); // Denver
		map2.put("29.4241200,-98.4936300","San Antonio"); // San Antonio
		map2.put("36.1749700,-115.1372200","Las Vegas"); // Las Vegas
		map2.put("39.7683800,-86.1580400","Indianapolis"); // Indianapolis
		map2.put("30.2671500,-97.7430600","Austin"); // Austin
		map2.put("25.7742700,-80.1936600","Miami"); // Miami
		map2.put("37.8043700,-122.2708000","Oakland"); // Oakland
		map2.put("41.3081500,-72.9281600","New Haven"); // New Haven
		map2.put("32.7765700,-79.9309200","Charleston"); // Charleston
		map2.put("40.7607800,-111.8910500","Salt Lake City"); // Salt Lake City
		map2.put("28.5383400,-81.3792400","Orlando"); // Orlando
		map2.put("33.5206600,-86.8024900","Birmingham"); // Birmingham
		map2.put("35.4675600,-97.5164300","Oklahoma City"); // Oklahoma City
		map2.put("45.5234500,-122.6762100","Portland"); // Portland
		
		String wylosowana;
		int index = 0;
		
		// pomijamy header
		line = br.readLine();
		// zliczamy ilosc postow z poszczegolnych miast
		while ((line = br.readLine()) != null) {
		
			// Losuje wspolrzedne geograficzne
			index = randomGenerator.nextInt(lista.size());
			wylosowana = lista.get(index);
						
			// zwiekszamy licznik o 1 danego miasta( wspolrzednych )
			map.put(wylosowana,map.get(wylosowana)+1);
		}
		
		String[] wspolrzedne;
		// dla ostaniego miasta string bez , na koncu
		//int liczba_miast = map.size();
		int id = 1;
		
		String outGeojson = "";
		for (Map.Entry<String, Integer> entry : map.entrySet()){
			
			out.println("{ \"index\" : { \"_index\" : \"geo\", \"_type\" : \"cities\", \"_id\" : \""+ id +"\" } }");
			
			outGeojson = "{ \"Country\": \"United States of America\", ";
			outGeojson += "\"City\": \""+ map2.get(entry.getKey())+"\" , ";
			outGeojson += "\"Tweets\": \""+ entry.getValue()+"\", ";
			
			// trzeba zamienic wspolrzedne kolejnoscia, wiec wyciagam szerokosc i dlugosc
			wspolrzedne = entry.getKey().split(",");
			
			outGeojson += " \"Location\": ["+wspolrzedne[1]+","+wspolrzedne[0]+"]";
			outGeojson += "}";
			out.println(outGeojson);
			id++;
		}

		out.close();
		br.close();
		System.exit(1); 
	}
	
}



















