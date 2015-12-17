	
	// Hinzufügen am Anfang der Klasse
	private long time;
	HashMap<String, Integer> playerCount = new HashMap<String, Integer>();
	HashMap<String, Long> playerTime = new HashMap<String, Long>();
	HashMap<String, Boolean> playerTimeout = new HashMap<String, Boolean>();
	
	
	//Funktion austauschen
	@Override
	public void onItemPickUp(EntityItemPickupEvent event) {
		time = System.currentTimeMillis();
		String name = event.entityPlayer.getDisplayName();

		// First time events triggers for player. Fill list to avoid null
		if (!playerTime.containsKey(name)) {
			playerTime.put(name, time);
			playerCount.put(name, 1);
			playerTimeout.put(name, false);
		}
		// Time in Millisecond to timeout. When outside this timeframe reset
		// counting to 1
		if ((time - playerTime.get(name)) > 3000) {
			playerCount.put(name, 1);
			playerTime.put(name, time);
			playerTimeout.put(name, false);
		} else if(!playerTimeout.get(name)){ // When inside the timeframe add 1 to the counter. If already there is already a timeout. No need to check again.
			playerCount.put(name, playerCount.get(name) + 1);
			if (playerCount.get(name) > 30) { // if counter reaches 30. add timeout for the rest of the duration.
				playerTimeout.put(name, true);
			}
		}

		if (!playerTimeout.get(name) && event.entityPlayer.inventory.inventoryChanged) {
			countItems(event.entityPlayer, event.item.getEntityItem());
		}
	}