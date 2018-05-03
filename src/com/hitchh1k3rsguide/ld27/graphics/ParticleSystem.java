package com.hitchh1k3rsguide.ld27.graphics;

import java.util.ArrayList;

import com.hitchh1k3rsguide.ld27.utilities.Vec2DPoint;

public class ParticleSystem
{

	public static class Particle
	{
		private AnimatedSprite sprite;
		private Vec2DPoint vel, grav, pos;
		private double delay;
		private double life = 0.77;
		private double time;
		public Particle(String name, Vec2DPoint pos, Vec2DPoint vel, Vec2DPoint grav, double delay, int size, int frames, double rotSpeed)
		{
			this.pos = pos;
			this.vel = vel;
			this.grav = grav;
			this.delay = delay;
			sprite = new AnimatedSprite(SpriteSheet.sprites, name, -size/2, -size/2, size, size, frames, 0.1);
			time = 0;
		}
		public void update(double frameTime)
		{
			this.time += frameTime;
			if(this.time > delay && this.time < delay+life)
			{
				Vec2DPoint adder = new Vec2DPoint(grav);
				adder.scale(frameTime);
				vel.addVector(adder);
				adder = new Vec2DPoint(vel);
				adder.scale(frameTime);
				pos.addVector(adder);
				sprite.update(frameTime);
			}
		}
		public void draw()
		{
			if(this.time > delay && this.time < delay+life)
			{
				sprite.setPos((int)pos.getDX(), (int)pos.getDY());
				sprite.draw();
			}
		}
	};

	private static ArrayList<ArrayList<Particle>> particles;
	private static boolean isInit = false;

	private static void init()
	{
		if(!isInit)
		{
			isInit = true;
			particles = new ArrayList<ArrayList<Particle>>(9);
			for(int i =0; i <= 8; ++i)
			{
				particles.add(new ArrayList<Particle>());
			}
		}
	}

	public static void addParticle(int zone, String type, int x, int y, int num, double delay)
	{
		init();
		if(type == "fire")
		{
			for(int i = 0; i < num; ++i)
				particles.get(zone).add(new Particle("explode", new Vec2DPoint(x, y), new Vec2DPoint((((Math.random()*10)-5)*3), (((Math.random()*10)-5)*3)), new Vec2DPoint(((Math.random()*5)-2.5), (Math.random()*5)-5), delay+(Math.random()*0.5), 32, 8, Math.random()*5));	
		}
		if(type == "glassL")
		{
			for(int i = 0; i < num; ++i)
				particles.get(zone).add(new Particle("glass."+((Math.random()*8)+1), new Vec2DPoint(x, y), new Vec2DPoint((((Math.random()*10)-10)*2), (Math.random()*4)-2), new Vec2DPoint(((Math.random()*4)-2), (Math.random()*5)-15), delay, 16, 8, 5));	
		}
		if(type == "glassR")
		{
			for(int i = 0; i < num; ++i)
				particles.get(zone).add(new Particle("glass."+((Math.random()*8)+1), new Vec2DPoint(x, y), new Vec2DPoint(((Math.random()*10)*2), (Math.random()*4)-2), new Vec2DPoint(((Math.random()*4)-2), (Math.random()*5)-15), delay, 16, 8, 5));	
		}
	}

	public static void clearParticles(int zone)
	{
		init();
		particles.get(zone).clear();
	}

	public static void updateParticles(int zone, double time)
	{
		init();
		for(int i = 0; i < particles.get(zone).size(); ++i)
		{
			particles.get(zone).get(i).update(time);
		}
	}

	public static void drawParticles(int zone)
	{
		init();
		for(int i = 0; i < particles.get(zone).size(); ++i)
		{
			particles.get(zone).get(i).draw();
		}
	}

}
