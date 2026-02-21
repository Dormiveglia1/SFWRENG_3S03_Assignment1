/**
 * Stub implementation of CatanAgent for testing core game mechanics.
 * Provides deterministic, minimal-logic responses to enable 
 * game flow testing without AI complexity.
 */
public class StubCatanAgent implements CatanAgent {
    
    private int playerId;
    
    @Override
    public void init(int playerId) {
        this.playerId = playerId;
        // No complex initialization needed for stub
    }
    
    @Override
    public Move chooseInitialSettlement(GameState state) {
        // Always choose the first valid settlement position
        List<Position> validPositions = state.getValidSettlementPositions(playerId);
        if (validPositions.isEmpty()) {
            throw new IllegalStateException("No valid settlement positions available");
        }
        return new Move(MoveType.BUILD_SETTLEMENT, validPositions.get(0));
    }
    
    @Override
    public Move chooseInitialRoad(GameState state) {
        // Always choose the first valid road position
        List<Position> validRoads = state.getValidRoadPositions(playerId);
        if (validRoads.isEmpty()) {
            throw new IllegalStateException("No valid road positions available");
        }
        return new Move(MoveType.BUILD_ROAD, validRoads.get(0));
    }
    
    @Override
    public Move chooseMove(GameState state) {
        // Simple strategy: try to build if resources allow, otherwise end turn
        if (state.canBuildRoad(playerId)) {
            List<Position> validRoads = state.getValidRoadPositions(playerId);
            if (!validRoads.isEmpty()) {
                return new Move(MoveType.BUILD_ROAD, validRoads.get(0));
            }
        }
        // Default: end turn
        return new Move(MoveType.END_TURN);
    }
    
    @Override
    public Map<ResourceType, Integer> chooseDiscard(GameState state, int discardCount) {
        // Discard resources in a fixed priority order
        Map<ResourceType, Integer> playerResources = state.getPlayerResources(playerId);
        Map<ResourceType, Integer> toDiscard = new HashMap<>();
        
        int remaining = discardCount;
        ResourceType[] priority = {ResourceType.WOOD, ResourceType.BRICK, 
                                   ResourceType.SHEEP, ResourceType.WHEAT, 
                                   ResourceType.ORE};
        
        for (ResourceType type : priority) {
            int available = playerResources.getOrDefault(type, 0);
            int discard = Math.min(available, remaining);
            if (discard > 0) {
                toDiscard.put(type, discard);
                remaining -= discard;
            }
            if (remaining == 0) break;
        }
        
        return toDiscard;
    }
    
    @Override
    public ResourceType chooseResource(GameState state) {
        // Always choose WOOD (arbitrary but deterministic)
        return ResourceType.WOOD;
    }
    
    @Override
    public int chooseRobberTarget(GameState state, List<Integer> possibleTargets) {
        // Choose the first available target, or -1 if none
        return possibleTargets.isEmpty() ? -1 : possibleTargets.get(0);
    }
    
    @Override
    public DevelopmentCard chooseDevelopmentCard(GameState state) {
        // Don't play development cards in stub implementation
        return null;
    }
}
