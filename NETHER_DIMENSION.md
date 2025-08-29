# Custom Nether Dimension

This mod implements a custom nether dimension that replaces the default nether (dimension -1) with enhanced biome variety similar to Minecraft 1.16.

## Biomes Implemented

### 1. Warped Forest (existing, enhanced)
- **Appearance**: Blue-green tinted biome with warped nylium ground
- **Features**: 
  - Warped trees with warped wart block canopies
  - Warped fungi and roots decoration
  - Nether sprouts undergrowth
- **Blocks**: Warped Nylium (top), Netherrack (filler)

### 2. Crimson Forest (new)
- **Appearance**: Red-tinted biome with crimson nylium ground
- **Features**:
  - Crimson trees with crimson planks canopies
  - Crimson fungi and roots decoration  
  - Nether wart undergrowth
- **Blocks**: Crimson Nylium (top), Netherrack (filler)

### 3. Soul Sand Valley (new)
- **Appearance**: Desolate valley with soul blocks
- **Features**:
  - Soul sand patches and valleys
  - Soul fire features
  - Bone block fossil structures
- **Blocks**: Soul Soil (top), Soul Sand (filler)

### 4. Basalt Deltas (new)
- **Appearance**: Rocky volcanic biome with basalt formations
- **Features**:
  - Basalt pillars of varying heights
  - Blackstone and basalt patches
  - Magma block heat sources
- **Blocks**: Blackstone (top), Smooth Basalt (filler)

### 5. Nether Wastes (vanilla)
- **Appearance**: Traditional nether appearance 
- **Features**: Standard nether terrain
- **Blocks**: Netherrack

## Implementation Details

### BiomeProviderNetherCustom
- Replaces default nether biome generation
- Uses hash-based distribution for biome variety
- Creates larger biome regions (200x200 blocks)
- Ensures even distribution of all 5 nether biomes

### Tree Generation
- `WorldGenMyNetherTree`: Generates warped trees
- `WorldGenCrimsonTree`: Generates crimson trees
- Both create organic, irregular canopy shapes

### Biome Registration
- All biomes registered in `ModBiomes` class
- Uses DESERT biome type for nether biomes
- Properly integrated with Forge biome system

## Usage

The custom nether dimension automatically replaces the default nether when the mod is loaded. Players can access it through normal nether portals and will experience the new biome variety.

To test biome generation, use the `NetherBiomeTest` class which verifies proper biome distribution across different coordinates.