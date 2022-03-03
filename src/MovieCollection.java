import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)ighest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    System.out.print("Enter a person to search for: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String cast = movies.get(i).getCast();
      cast = cast.toLowerCase();

      if (cast.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
   // sortResults(results);
    ArrayList<String> castResults = new ArrayList<String> ();
    int choiceNum = 0;
    // now, display them all to the user
    for (int a = 0; a < results.size(); a++)
    {
      String[] cast = results.get(a).getCast().split("\\|");
      // this will print index 0 as choice 1 in the results list; better for user!
      for (int j = 0; j < cast.length; j++)
      {
        if (castResults.indexOf(cast[j]) == -1 && cast[j].toLowerCase().indexOf(searchTerm) != -1)
        {
          castResults.add(cast[j]);
        }
      }
    }
    alphabeticalOrder(castResults);
    for (int x = 0; x < castResults.size(); x++)
    {
      choiceNum++;
      System.out.println("" + choiceNum + ". " + castResults.get(x));
    }
    System.out.println("Who would you like to see all movies for?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();
    int choiceMovie = 0;
    String selectedCast = castResults.get(choice - 1);
    ArrayList<Movie> newMovies = new ArrayList<Movie> ();
    // ArrayList<String> movieNames = new ArrayList<String> ();

    for (int i = 0; i < movies.size(); i++)
    {
      if (movies.get(i).getCast().indexOf(selectedCast) != -1)
      {
        newMovies.add(movies.get(i));
        // movieNames.add(movies.get(i).getTitle());
      }
    }
    alphabeticalOrderMovie(newMovies);
    for (int y = 0; y < newMovies.size(); y++)
    {
      if (newMovies.get(choiceMovie).getCast().indexOf(selectedCast) != -1)
      {
        choiceMovie++;
        System.out.println("" + choiceMovie + ". " + newMovies.get(y).getTitle());
      }
    }
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int secondChoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = newMovies.get(secondChoice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void searchKeywords()
  {
    System.out.print("Enter a keyword search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieKeyword = movies.get(i).getKeywords();
      movieKeyword = movieKeyword.toLowerCase();

      if (movieKeyword.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);
    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listGenres()
  {
    ArrayList<Movie> results = new ArrayList<Movie>();
    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String genres = movies.get(i).getGenres();
      results.add(movies.get(i));
    }

    // sort the results by title
    sortResults(results);
    ArrayList<String> genreResults = new ArrayList<String> ();
    int choiceNum = 0;
    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String[] genres = results.get(i).getGenres().split("\\|");
      // this will print index 0 as choice 1 in the results list; better for user!
      for (int j = 0; j < genres.length - 1; j++)
      {
        String genreMem = genres[j];
        if (genreResults.indexOf(genreMem) == -1)
        {
          genreResults.add(genres[j]);
        }
      }
    }
    alphabeticalOrder(genreResults);
    for (int a = 0; a < genreResults.size(); a++)
    {
      choiceNum++;
      System.out.println("" + choiceNum + ". " + genreResults.get(a));
    }

    System.out.println("Which would you like to see all movies for?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();
    int choiceMovie = 0;
    String selectedGenre = genreResults.get(choice - 1);
    ArrayList<Movie> newMovies = new ArrayList<Movie> ();
    for (int i = 0; i < movies.size(); i++)
    {
      if (movies.get(i).getGenres().indexOf(selectedGenre) != -1)
      {
        newMovies.add(movies.get(i));
      }
    }
    alphabeticalOrderMovie(newMovies);
    for (int g = 0; g < newMovies.size(); g++)
    {
      choiceMovie++;
      System.out.println("" + choiceMovie + ". " + newMovies.get(g).getTitle());
    }
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int secondChoice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = newMovies.get(secondChoice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listHighestRated()
  {
//    ArrayList <String> top50RatingName = new ArrayList <String> ();
//    ArrayList <Double> top50RatingNum = new ArrayList <Double> ();
//    ArrayList<Movie> top50 = new ArrayList<Movie> ();
//    double max = 0.0;
//    for (int i = 0; i < movies.size(); i++)
//    {
//      if (movies.get(i).getUserRating() > max)
//      {
//        max = movies.get(i).getUserRating();
//        top50RatingName.add(movies.get(i).getTitle());
//        top50RatingNum.add(movies.get(i).getUserRating());
//      }
//    }
//    numericalOrder(top50);
//    for (int a = 0; a < 49; a++)
//    {
//      top50.set(top50.get(a).getUserRating(), top50RatingNum.get(a));
//    }

    numericalOrder(movies);

    for (int i = 0; i < 50; i++)
    {
      String count = "" + i + 1;
      System.out.println(count + ". " + movies.get(i).getTitle() + " " + movies.get(i).getUserRating());
    }
  }
  
  private void listHighestRevenue()
  {
   revenueOrder(movies);
   for (int i = 0; i < 50; i++)
   {
     String count = "" + i + 1;
     System.out.println(count + ". " + movies.get(i).getTitle() + " " + movies.get(i).getRevenue());
   }
  }

  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies = new ArrayList<Movie>();

      while ((line = bufferedReader.readLine()) != null)
      {
        // import all cells for a single row as an array of Strings,
        // then convert to ints as needed
        String[] movieFromCSV = line.split(",");

        // pull out the data for this cereal
        String title = (movieFromCSV[0]);
        String cast = (movieFromCSV[1]);
        String director = (movieFromCSV[2]);
        String tagline = (movieFromCSV[3]);
        String keywords = (movieFromCSV[4]);
        String overview = (movieFromCSV[5]);
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = (movieFromCSV[7]);
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        Movie nextMovie = new Movie(title, cast, director, tagline, keywords,
                overview, runtime, genres, userRating, year, revenue);
        movies.add(nextMovie);
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      System.out.println("Unable to access " + exception.getMessage());
    }
  }
  private void alphabeticalOrder(ArrayList<String> words)
  {
    int count = 0;
    for (int j = 1; j < words.size(); j++)
    {
      String temp = words.get(j);
      int possibleIndex = j;
      while (possibleIndex > 0 && temp.compareTo(words.get(possibleIndex - 1)) < 0)
      {
        count++;
        words.set(possibleIndex, words.get(possibleIndex - 1));
        possibleIndex--;
      }
      words.set(possibleIndex, temp);
    }
  }
  private void alphabeticalOrderMovie(ArrayList<Movie> words)
  {
    int count = 0;
    for (int j = 1; j < words.size(); j++)
    {
      Movie temp = words.get(j);
      int possibleIndex = j;
      while (possibleIndex > 0 && temp.getTitle().compareTo(words.get(possibleIndex - 1).getTitle()) < 0)
      {
        count++;
        words.set(possibleIndex, words.get(possibleIndex - 1));
        possibleIndex--;
      }
      words.set(possibleIndex, temp);
    }
  }

  private void numericalOrder(ArrayList<Movie> movieList)
  {
//    int count = 0;
//    for (int j = 0; j < num.size() - 1; j++)
//    {
//      int minIndex = j;
//      for (int k = j + 1; k < num.size(); k++)
//      {
//        count++;
//        if (num.get(k).getUserRating() < (num.get(minIndex).getUserRating()))
//        {
//          minIndex = k;
//        }
//      }
//      Movie temp = num.get(j);
//      num.set(j, num.get(minIndex));
//      num.set(minIndex, temp);
    int count = 0;
    for (int j = 1; j < movieList.size(); j++)
    {
      Movie temp = movieList.get(j);
      int possibleIndex = j;
      while (possibleIndex > 0 && temp.getUserRating() > (movieList.get(possibleIndex - 1).getUserRating()))
      {
        count++;
        movieList.set(possibleIndex, movieList.get(possibleIndex - 1));
        possibleIndex--;
      }
      movieList.set(possibleIndex, temp);
    }
  }
  private void revenueOrder(ArrayList <Movie> movieList)
  {
    int count = 0;
    for (int j = 1; j < movieList.size(); j++) {
      Movie temp = movieList.get(j);
      int possibleIndex = j;
      while (possibleIndex > 0 && temp.getRevenue() > (movieList.get(possibleIndex - 1).getRevenue())) {
        count++;
        movieList.set(possibleIndex, movieList.get(possibleIndex - 1));
        possibleIndex--;
      }
      movieList.set(possibleIndex, temp);
    }
  }
}