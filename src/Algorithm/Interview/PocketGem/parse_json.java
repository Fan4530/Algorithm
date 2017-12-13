package Algorithm.Interview.PocketGem;
import java.util.*;
public class parse_json {
    public List<HashMap<String, String>> parseJSON(Json apiData, String[] columns)
    {
        List<HashMap<String, String>> res = new LinkedList<HashMap<String, String>>();
        if (apiData == null || columns == null)
            return res;
        dfs(apiData, columns, res, 0, new HashMap<String, String>());
        return res;
    }
    public static void dfs(Json apiData, String[] column, List<HashMap<String, String>> res,
        int index, HashMap<String, String> path)
    {
        String[] Keys = apiData.keys();  //Get key array
        //Exit:
        //1. If apiData return jsonString, Even though we haven't walk through the column, but we can't go further,
        //So we need to add into res, then backtracking: Delete the last node(hashtable..), and go to another path
        if (apiData.type() == "JSONString")
        {
            path.put(column[index], Keys[0]);
            res.add(new HashMap<String, String>(path));
            path.remove(column[index]);
            return;
        }
        //2. If we reach the last of column, add path into res
        if (index == column.length)
        {
            res.add(new HashMap<String, String>(path));
            return;
        }
        //Using the API to get the key array within the JSONMapping.
        //For example: when column[i] = country(1st level), the they key is {GB, US}, dfs all the key, math keys[i] and column[index]
        for (int i = 0; i < Keys.length; i++)
        {
            path.put(column[index], Keys[i]);
            dfs(apiData.get(Keys[i]), column, res, index + 1, path);
            path.remove(column[index]);   //Back to the start point, keep looking for next next branch.
        }
    }
    class Json {
        String json;
        public Json(){}
        public Json(String json) {this.json = json;}
        public String toString(){
            return "";
        }
        public String type() {
            return "";
        }
        public String value() {
            return "";
        }
        public Json get(String key) {return null;}
        public String[] keys() {return new String[2];}
    }
    class JSONString extends Json{
        public String value(){return "";}
        public String type(){return "";}
    }
}
