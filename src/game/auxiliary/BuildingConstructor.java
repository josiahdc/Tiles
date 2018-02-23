package game.auxiliary;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import buildings.constructs.Construct;
import game.control.Game;
import game.control.Tile;
import game.enums.ConstructDirectory;

public class BuildingConstructor {

	// uses reflection to construct an object of the type specified by the className param
	public static Construct buildConstruct(Tile[] tiles, Game game, String className) {
		try {
			Class<?> constructClass = Class.forName("buildings.constructs."+ className);
			Constructor<?> ctor = constructClass.getConstructor(Tile[].class, Game.class);
			Construct construct = (Construct) ctor.newInstance(tiles, game);
			return construct;
		} catch (ClassNotFoundException | InstantiationException |
					IllegalAccessException | IllegalArgumentException |
					InvocationTargetException | NoSuchMethodException |
					SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}