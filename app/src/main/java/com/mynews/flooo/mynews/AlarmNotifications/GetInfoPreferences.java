package com.mynews.flooo.mynews.AlarmNotifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetInfoPreferences
{

    private SharedPreferences sharedPreferences;
    private static HashMap<String, Boolean> checkBoxStates = new HashMap<String, Boolean>();
    private static String queryTerm;

    public static void declaresVariablesWidgets(ArrayList<CheckBox> listCheckBox,String q)
    {

        checkBoxStates.clear();
        for (CheckBox checkBox:listCheckBox)
        {
            checkBoxStates.put(checkBox.getTag().toString(),checkBox.isChecked());

        }

       queryTerm=q;

    }

    private String queryTermBuild(String input)
    {
        StringBuilder queryTermString =new StringBuilder();

        for (String value: input.split(" "))
        {
            queryTermString.append(value);
            queryTermString.append("+");

        }

        return queryTermString.toString();
    }

    public String getQueryTermofView()
    {
        return queryTermBuild(queryTerm);
    }

    public String buildSectionsStringForNotification(Context c)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);

        checkBoxStates.clear();


        checkBoxStates.put("Arts", sharedPreferences.getBoolean("Arts",true));
        checkBoxStates.put("Sports", sharedPreferences.getBoolean("Sports",true));
        checkBoxStates.put("Politics", sharedPreferences.getBoolean("Politics",true));
        checkBoxStates.put("Entrepreneurs", sharedPreferences.getBoolean("Entrepreneurs",true));
        checkBoxStates.put("Business", sharedPreferences.getBoolean("Business",true));
        checkBoxStates.put("Travel", sharedPreferences.getBoolean("Travel",true));

        return buildNewsDeskQuery();

    }

    public String buildSectionsStringForSearch()
    {
        return buildNewsDeskQuery();
    }

    private String buildNewsDeskQuery()
    {
        StringBuilder sectionsString = new StringBuilder();
        sectionsString.append("news_desk:(");

        for(Map.Entry<String, Boolean> entry : checkBoxStates.entrySet())
        {

            if(entry.getValue())
            {
                sectionsString.append(" ");
                sectionsString.append(entry.getKey());
                sectionsString.append(", ");

            }

        }

        sectionsString.deleteCharAt(sectionsString.length()-2);
        sectionsString.append(")");
        String sections= sectionsString.toString();

        return sections;
    }
}
