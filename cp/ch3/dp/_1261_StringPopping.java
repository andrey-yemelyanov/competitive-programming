package helvidios.cp.ch3.dp;

import java.util.*;

import static java.lang.Math.*;

public class _1261_StringPopping {
    static class Group{
        public int from;
        public int to;
        public int length(){return to - from + 1;}
        public Group(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
    public static void main(String[] args) {
        String data = "3\nbabbbaaabb\naabbaabb\nabab";
        Scanner scanner = new Scanner(data);
        int nTests = scanner.nextInt();
        while(nTests-- > 0){
            String s = scanner.next();
            memo = new HashSet<>();
            if(popString(s))System.out.println(1);
            else System.out.println(0);
        }
        scanner.close();
    }

    static String popGroup(String s, Group g){
        return s.substring(0, g.from) + s.substring(g.to + 1);
    }

    static Group[] getGroups(String s){
        List<Group> groups = new ArrayList<>();
        if(s.length() > 0){
            char groupId = s.charAt(0); int from = 0; int to = 0;
            for(int i = 1; i < s.length(); i++){
                if(s.charAt(i) != groupId){
                    groupId = s.charAt(i);
                    groups.add(new Group(from, to));
                    from = i; to = i;
                }else to++;
            }
            groups.add(new Group(from, to));
        }
        return groups.toArray(new Group[0]);
    }

    static Set<String> memo;
    static boolean popString(String s){
        //System.out.printf("popString \"%s\"\n", s);
        if(s.length() == 0) return true;
        if(memo.contains(s)) return false;
        for(Group g : getGroups(s)){
            if(g.length() >= 2 && popString(popGroup(s, g))) return true;
        }
        memo.add(s);
        return false;
    }
}
