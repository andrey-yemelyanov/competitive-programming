import java.util.*;
import static java.lang.Math.*;
import java.util.stream.*;

/*
Problem name: 11697 Playfair Cipher
Problem url: https://uva.onlinejudge.org/external/116/11697.pdf
Author: Andrey Yemelyanov
*/
public class _11697 {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int nTests = s.nextInt(); s.nextLine();
    while(nTests-- > 0){
      String key = s.nextLine();
      String plaintext = s.nextLine();
      System.out.println(encrypt(plaintext, key));
    }
  }

  static String encrypt(String plaintext, String key){
    StringBuilder cipher = new StringBuilder();
    encrypt(new StringBuilder().append(normalize(plaintext)), buildTable(key), cipher);
    return cipher.toString();
  }

  static void encrypt(StringBuilder plaintext, char[][] table, StringBuilder cipher){
    if(plaintext.length() == 0) return;
    char c1 = plaintext.charAt(0);
    if(plaintext.length() == 1){
      cipher.append(encryptGroup(c1, 'X', table));
      return;
    }
    char c2 = plaintext.charAt(1);
    if(c1 == c2){
      cipher.append(encryptGroup(c1, 'X', table));
      plaintext.deleteCharAt(0);
      encrypt(plaintext, table, cipher);
    }else{
      cipher.append(encryptGroup(c1, c2, table));
      plaintext.deleteCharAt(0);
      plaintext.deleteCharAt(0);
      encrypt(plaintext, table, cipher);
    }
  }

  static String encryptGroup(char c1, char c2, char[][] table){
    int[] c1Coord = findCharInTable(c1, table);
    int[] c2Coord = findCharInTable(c2, table);
    int row1 = c1Coord[0];
    int row2 = c2Coord[0];
    int col1 = c1Coord[1];
    int col2 = c2Coord[1];
    if(row1 == row2) return new String(new char[]{
      table[row1][(col1 + 1) % table.length],
      table[row1][(col2 + 1) % table.length]
    });
    if(col1 == col2) return new String(new char[]{
      table[(row1 + 1) % table.length][col1],
      table[(row2 + 1) % table.length][col1]
    });
    return new String(new char[]{
      oppositeCharInRectangle(c1Coord, c2Coord, table),
      oppositeCharInRectangle(c2Coord, c1Coord, table)
    });
  }

  static char oppositeCharInRectangle(int[] c1Coord, int[] c2Coord, char[][] table){
    return table[c1Coord[0]][c2Coord[1]];
  }

  static int[] findCharInTable(char c, char[][] table){
    for(int i = 0; i < table.length; i++){
      for(int j = 0; j < table.length; j++){
        if(table[i][j] == c) return new int[]{i, j};
      }
    }
    return null;
  }

  static char[] normalize(String text){
    StringBuilder sb = new StringBuilder();
    for(char c : text.toUpperCase().toCharArray()){
      if(Character.isLetter(c)){
        sb.append(c);
      }
    }
    return sb.toString().toCharArray();
  }

  static char[][] buildTable(String key){
    char[][] table = new char[5][5];
    boolean[] alphabet = new boolean[26];
    int row = 0; int col = 0;
    for(char c : normalize(key)){
      if(!alphabet[c - 'A']){
        alphabet[c - 'A'] = true;
        table[row][col] = c;
        col++;
        if(col == table.length){
          col = 0; row++;
        }
      }
    }
    for(int i = 0; i < alphabet.length && row < table.length; i++){
      char c = (char)('A' + i);
      if(!alphabet[i] && c != 'Q'){
        table[row][col] = c;
        col++;
        if(col == table.length){
          col = 0; row++;
        }
      }
    }
    return table;
  }
}
