package com.test.main.util;

import com.test.main.entity.tiles.Ground;
import com.test.main.util.base.Entity;
import com.test.main.util.handlers.ClassInstantiator;
import com.test.main.util.handlers.EntityHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LevelLoader {
    JSONObject en;
    JSONObject ti;

    public LevelLoader(String level, EntityHandler eh) {
        ClassInstantiator ci = new ClassInstantiator();
        String file = "res/level/"+level+".json";
        try {
            Object o = new JSONParser().parse(new FileReader(file));
            en = (JSONObject)(new JSONParser().parse(new FileReader("res/id/entities.json")));
            ti = (JSONObject)(new JSONParser().parse(new FileReader("res/id/tiles.json")));
            JSONObject j = (JSONObject)o;
            JSONArray jA = (JSONArray)j.get("tiles");
            JSONArray iA = (JSONArray)j.get("entities");
            JSONArray oA = (JSONArray)j.get("camOffset");
            for (Object value : jA) {
                JSONArray tA = (JSONArray)value;
                int x1 = (int)((long) tA.get(1));
                int y1 = (int)((long) tA.get(2));
                int x2 = (int)((long) tA.get(3));
                int y2 = (int)((long) tA.get(4));
                eh.addEntity((Entity)ci.instantiate((String)"com.test.main.entity."+(String)ti.get((String)tA.get(0)),eh,x1,y1,x2,y2));
            }
            for (Object value : iA) {
                JSONArray eA = (JSONArray)value;
                int x = (int)((long)eA.get(1));
                int y = (int)((long)eA.get(2));
                eh.addEntity((Entity)ci.instantiate((String)"com.test.main.entity."+(String)en.get((String)eA.get(0)),eh,x,y));
            }
            EnvironmentVariables.CAM.setXScale((double)j.get("camScale"));
            EnvironmentVariables.CAM.setYScale((double)j.get("camScale"));
            EnvironmentVariables.CAM.setMinX((double)oA.get(0));
            EnvironmentVariables.CAM.setMinY((double)oA.get(1));
            EnvironmentVariables.CAM.setMaxX((double)oA.get(2));
            EnvironmentVariables.CAM.setMaxY((double)oA.get(3));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
