package lince.plugins;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by deicos on 09/06/2015.
 */
public enum HoisanVars {
   OBSERVATION_TIMES_TABLE_NAME("TiemposObservaciones"),
   OBSERVATION_CATEGORY_TABLE_NAME("TiemposCategoriasObservadas"),

   TIME_VIDEO_NAME("Nombre_Video"),
   TIME_INITIAL_FRAME("TFramesInicial"),
   TIME_ID("ID_Tiempo"),

   CRITERIA_TABLE_NAME("Criterios"),
   CRITERIA_TYPE("Tipo"),
   CRITERIA_TYPE_FIXED("Fijo"),
   CRITERIA_TYPE_NORMAL("Normal"),
   CRITERIA_NAME("Nombre_Criterio"),
   CRITERIA_DESCRIPTION("Descripcion"),
   CRITERIA_ID("ID_Criterio"),

   CATEGORY_TABLE_NAME("Categorias"),
   CATEGORY_CRITERIA_ID("ID_Criterio_Referencial"),
   CATEGORY_ID("ID_Categoria"),
   CATEGORY_NAME("Nombre_Categoria"),
   CATEGORY_CORE("Nucleo_Categorial"),
   CATEGORY_DESCRIPTION("Descripcion"),
   mode2("Mode2");

   private final String name;

   private HoisanVars(String s) {
      name = s;
   }

   public boolean equals(String otherName) {
      return StringUtils.equals(name, otherName);
   }

   public String toString() {
      return name;
   }

   public String getStringValue(Map<String, Object> row) {
     return StringUtils.defaultString(this.<String>getRawValue(row));
   }

   public <T> T getRawValue(Map<String, Object> row) {
      try {
         if (row != null) {
            return (T) row.get(name);
         }
      } catch (Exception e) {
         Logger.getLogger(HoisanVars.class.getName()).error(e);
      }
      return null;
   }

   public Integer getIntValue(Map<String, Object> row) {
      return this.<Integer>getRawValue(row);
   }


}