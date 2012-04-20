
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Application;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureDao;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureEvolutionDao;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureRaiseTypeDao;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureStateDao;
import com.redditandroiddevelopers.tamagotchi.dao.ExperienceDao;
import com.redditandroiddevelopers.tamagotchi.dao.MedicineDao;
import com.redditandroiddevelopers.tamagotchi.dao.SicknessDao;

/**
 * A place to store runtime configuration for use throughout the lifetime of a
 * {@link TamagotchiGame} object. Not to be confused with a store for game
 * settings.
 * 
 * @author Santoso Wijaya
 */
public class TamagotchiConfiguration {

    public boolean debug = true;
    public boolean logFps = true;
    public int logLevel = Application.LOG_DEBUG;
    public float stageWidth = 800;
    public float stageHeight = 480;
    
    // Persistence
    public CreatureDao creatureDao;
    public CreatureEvolutionDao creatureEvolutionDao;
    public CreatureRaiseTypeDao creatureRaiseTypeDao;
    public CreatureStateDao creatureStateDao;
    public ExperienceDao experienceDao;
    public MedicineDao medicineDao;
    public SicknessDao sicknessDay;

}
