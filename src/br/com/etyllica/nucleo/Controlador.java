package br.com.etyllica.nucleo;

public class Controlador extends Thread{
	
	private Componente p;
	
	//Baseado na Nostaljia
	private long initialTime = 0;
	private long currentTime = 0;
	private long passedTime = 0;
	private int framerate = 50;
	private int period = 1000 / framerate;
	private int framesSkipped = 0;
	private final int MAX_FRAME_SKIPS = 5;
	private static final int NO_DELAYS_PER_YIELD = 16;
	private Thread looping;
	private boolean isRunning = false;
	
	public Controlador(Componente p){
		this.p = p;
	}
	public Controlador(Componente p, int fps){
		this.p = p;
		this.framerate = fps;
	}
	
	public void start()
	{
		if ( looping == null || ! isRunning )
		{
			looping = new Thread( this );
			isRunning = true;
			looping.start();
		}	
	}

	public void run()
	{
		long beforeTime, afterTime, timeDiff, sleepTime;
		long aux = 0;
		int overSleepTime = 0;
		int noDelays = 0;
		int excess = 0;

		beforeTime = System.currentTimeMillis();

		while ( true )
		{

			//p.gerencia();
			p.desenha();

			afterTime = System.currentTimeMillis();

			// If the OS is powered on more than a month (aproximately 47.7 days in the case of Windows),
			// the current time count restarts, this is a workaround to avoid problems
			if ( afterTime < beforeTime )
			{
				beforeTime = afterTime;
			}

			timeDiff = afterTime - beforeTime;
			sleepTime = ( period - timeDiff ) - overSleepTime;

			currentTime = System.currentTimeMillis();

			aux = passedTime;

			passedTime = ( currentTime - initialTime ) / 1000;

			if ( aux == passedTime - 1 )
			{
				aux = passedTime;
			}

			if ( passedTime == 0 )
			{
				passedTime = 1;
			}

			// some time left in this cycle
			if ( sleepTime > 0 )
			{
				try
				{
					Thread.sleep( sleepTime ); // already in miliseconds
				}

				catch ( InterruptedException e )
				{
					//Log.error( getClass(), e );
				}

				overSleepTime = (int) ( ( System.currentTimeMillis() - afterTime ) - sleepTime );
			}

			else
			{
				// sleepTime <= 0; the frame took longer than the period
				excess -= sleepTime; // store excess time value
				overSleepTime = 0;

				if ( ++noDelays >= NO_DELAYS_PER_YIELD )
				{
					// give another thread a chance to run

					Thread.yield();
					noDelays = 0;
				}
			}

			beforeTime = System.currentTimeMillis();

			// If frame animation is taking too long, update the game state without rendering it, to
			// get the updates/sec nearer to the required FPS.
			int skips = 0;

			while ( ( excess > period ) && ( skips < MAX_FRAME_SKIPS ) )
			{
				excess -= period;

				// update state but don't render
				//engineUpdate();
				p.gerencia();
				skips++;
			}

			framesSkipped += skips;

			// To avoid problems with the window, if is running like an
			// application, or the browser, if it is running from web, must not
			// request the focus every time, only in the beginning.
		}
	}
	

	public int getFramesSkipped(){
		return framesSkipped;
	}
	
}