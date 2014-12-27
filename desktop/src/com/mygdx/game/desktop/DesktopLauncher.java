package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Astar;
import com.mygdx.game.AstarNode;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		startApp();
		//testBresenham();
		//testAstar();
	}

	private static void startApp(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.width = 1024;
		config.height = 768;
		config.resizable = true;
		new LwjglApplication(new MyGdxGame(), config);
	}

	private static void testBresenham(){
		Bresenham2 b = new Bresenham2();
		Array<GridPoint2> points = b.line(new GridPoint2(1,1), new GridPoint2(5,10));

		for(GridPoint2 p : points){
			System.out.println("x: " + p.x + " y: " + p.y );
		}
	}

	private static void testAstar(){
		Array<Array<AstarNode>> graph = new Array<Array<AstarNode>>();
		AstarNode start;
		AstarNode end;
		Astar astar = new Astar();
		for(int i = 0; i < 5; i++){
			graph.add(new Array<AstarNode>());
		}
		for (int i = 0; i < 5; i++){
			for(int  j = 0; j < 5; j++){
				AstarNode node = new AstarNode();
				node.passingCost = 1;
				node.x = i;
				node.y = j;
				graph.get(i).add(node);
			}
		}

		start = graph.get(0).get(0);
		end = graph.get(4).get(4);

		graph.get(4).get(0).passingCost = 1000f;
		graph.get(3).get(1).passingCost = 1000f;

		Array<AstarNode> path = astar.getPath(graph, start, end);

		for (AstarNode node : path){
			System.out.println("x: " + node.x + " y: " + node.y);
		}
	}
}
