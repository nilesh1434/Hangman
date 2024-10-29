import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;	

	ArrayList<HangmanLogic> games = new ArrayList<HangmanLogic>();
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	private int portNumber; //5555
	
	
	Server(Consumer<Serializable> call, int portNum){
	
		callback = call;
		portNumber = portNum;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(portNumber);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		    	HangmanLogic hm = new HangmanLogic(1);
				games.add(hm);
				ClientThread c = new ClientThread(mysocket.accept(), count, hm);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
			HangmanLogic game;
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			boolean partA = true;
			boolean partB = false;
			
			
			ClientThread(Socket s, int count, HangmanLogic hm){
				this.connection = s;
				this.count = count;	
				game = hm;
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				while(true) {
					
				if(partB) {
					game.reset();
					partB = false;
				}
				if(partA) {
				try {
					String data = in.readObject().toString();
					int x = Integer.parseInt(data);
					game.changeWordCatagorey(x);
					if(x == 1) {
						callback.accept("Word from Food Category");
				    	out.writeObject("Word from Food Category");
				    	out.reset();
				    } else if (x == 2) {
						callback.accept("Word from Countries Category");
				    	out.writeObject("Word from Countries Category");
				    	out.reset();
					} else if(x == 3) {
						callback.accept("Word from Sports Category");
				    	out.writeObject("Word from Sports Category");
				    	out.reset();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
				
				int size = game.getWordS();
				try {
					callback.accept("User: " + count + " Started Playing. Word Is: " + game.getWord());
					out.writeObject("Guess the Word! It is " + size + " letters long");
					out.reset();	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				partA = false;
				} else {
					
			    try {
			    	String data = in.readObject().toString();
			    	if(game.takeAGuess(data)) {
			    		callback.accept("User: " + count + " Guessed: " + data + " Right");
				    	out.writeObject("You Guessed: " + data);
				    	out.reset();
				    	out.writeObject(game.dispWord() + " is your current guess");
				    	out.reset();
				    	
			    	} else {
				    	callback.accept("User: " + count + " Guessed: " + data + " Wrong");
				    	out.writeObject("You Guessed: " + data);
				    	out.reset();
				    	out.writeObject("You have " + game.getLives() + " guesses left");
				    	out.reset();
				    	if(game.getLives() == 0) {
				    		callback.accept("User " + count + " Lost");
					    	out.writeObject("You Lost");
					    	out.reset();
					    	int lostGames;
					    	if(game.getTheme() == 1) {
					    		lostGames = game.getCat1();
					    		callback.accept("User " + count + " Lost In Theme 1. " + lostGames + " Rounds Lost");
					    		out.writeObject("You Lost In Foods. " + lostGames + " Rounds Lost");
					    		out.reset();
					    	} else if(game.getTheme() == 2) {
					    		lostGames = game.getCat2();
					    		callback.accept("User " + count + " Lost In Theme 2. " + lostGames + " Rounds Lost");
					    		out.writeObject("You Lost In Countries. " + lostGames + " Rounds Lost");
					    		out.reset();
					    	} else if(game.getTheme() == 3) {
					    		lostGames = game.getCat3();
					    		callback.accept("User " + count + " Lost In Theme 2. " + lostGames + " Rounds Lost");
					    		out.writeObject("You Lost In Sports. " + lostGames + " Rounds Lost");
					    		out.reset();
					    	}
					    	if(game.getLoser()) {
					    		callback.accept("User " + count + " Lost The Game");
					    		out.writeObject("You Lost The Game");
					    		out.reset();
					    		partB = true;
					    	}
					    	
					    	partA = true;
					    	continue;
				    	}

			    	}
			    	if(game.checkWin()) {
			    		callback.accept("User " + count + " Won");
			    		out.writeObject("You Won");
			    		out.reset();
			    		int a = game.getTheme();
			    		callback.accept("User " + count + " Beat Category: " + a);
			    		out.writeObject("You Beat Category: " + a);
			    		out.reset();
			    		if(game.getCat1b() && game.getCat2b() && game.getCat3b()) {
			    			out.writeObject("You have: " + game.getWins() + " Wins.");
				    		out.reset();
				    		out.writeObject("You Won The Game!");
				    		out.reset();
				    		partB = true;
				    	}
				    	partA = true;
				    	continue;
			    	}
			    	
			    	
			    	} catch(Exception e) {
			    		callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
			    		clients.remove(this);
			    		break;
			    	}
				}
			}
				
		}//end of run
			
			
		}//end of client thread
}