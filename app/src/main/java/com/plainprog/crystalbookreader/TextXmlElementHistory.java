package com.plainprog.crystalbookreader;

import java.util.ArrayList;

public class TextXmlElementHistory {
    ArrayList<String> nodeHistory;

    public TextXmlElementHistory(ArrayList<String> nodeHistory) {
        this.nodeHistory = nodeHistory;
    }

    public TextXmlElementHistory() {
        nodeHistory = new ArrayList<>();
    }

    public void addToHistory(String nodeName){
        nodeHistory.add(nodeName);
    }
    public void removeFromHistory(String nodeName){
        for (int i = nodeHistory.size(); i >= 0; i--){
            if (nodeHistory.get(i).equals(nodeName)){
                nodeHistory.remove(i);
                return;
            }
        }
    }

    public ArrayList<String> getNodeHistory() {
        return nodeHistory;
    }
    public boolean contains(String item){
        return  nodeHistory.contains(item);
    }
    public boolean hasAnyHeaderNode(){
        for (int i =0; i < nodeHistory.size(); i ++){
            String node = nodeHistory.get(i);
            if (node == "h1" || node == "h2" || node == "h3" || node == "h4" || node == "h5" || node == "h6")
                return true;
        }
        return  false;
    }
}
