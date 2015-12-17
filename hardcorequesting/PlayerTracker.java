
//Funktion Austauschen
@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;
        if (!player.worldObj.isRemote) {
            PacketHandler.remove(player);
        }
        QuestingData.savePlayerData(player.getDisplayName());
    }