package diacritics.owo.world.gen.noise;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.noise.NoiseRouter;

// for the sake of my own sanity, i generated pretty much all of this using some code
public class NoiseRouterBuilder {
  private DensityFunction barrierNoise;
  private DensityFunction fluidLevelFloodednessNoise;
  private DensityFunction fluidLevelSpreadNoise;
  private DensityFunction lavaNoise;
  private DensityFunction temperature;
  private DensityFunction vegetation;
  private DensityFunction continents;
  private DensityFunction erosion;
  private DensityFunction depth;
  private DensityFunction ridges;
  private DensityFunction initialDensityWithoutJaggedness;
  private DensityFunction finalDensity;
  private DensityFunction veinToggle;
  private DensityFunction veinRidged;
  private DensityFunction veinGap;

  public NoiseRouterBuilder(DensityFunction barrierNoise,
      DensityFunction fluidLevelFloodednessNoise, DensityFunction fluidLevelSpreadNoise,
      DensityFunction lavaNoise, DensityFunction temperature, DensityFunction vegetation,
      DensityFunction continents, DensityFunction erosion, DensityFunction depth,
      DensityFunction ridges, DensityFunction initialDensityWithoutJaggedness,
      DensityFunction finalDensity, DensityFunction veinToggle, DensityFunction veinRidged,
      DensityFunction veinGap) {
    this.barrierNoise = barrierNoise;
    this.fluidLevelFloodednessNoise = fluidLevelFloodednessNoise;
    this.fluidLevelSpreadNoise = fluidLevelSpreadNoise;
    this.lavaNoise = lavaNoise;
    this.temperature = temperature;
    this.vegetation = vegetation;
    this.continents = continents;
    this.erosion = erosion;
    this.depth = depth;
    this.ridges = ridges;
    this.initialDensityWithoutJaggedness = initialDensityWithoutJaggedness;
    this.finalDensity = finalDensity;
    this.veinToggle = veinToggle;
    this.veinRidged = veinRidged;
    this.veinGap = veinGap;
  }

  public NoiseRouterBuilder(NoiseRouter noiseRouter) {
    this(noiseRouter.barrierNoise(), noiseRouter.fluidLevelFloodednessNoise(),
        noiseRouter.fluidLevelSpreadNoise(), noiseRouter.lavaNoise(), noiseRouter.temperature(),
        noiseRouter.vegetation(), noiseRouter.continents(), noiseRouter.erosion(),
        noiseRouter.depth(), noiseRouter.ridges(), noiseRouter.initialDensityWithoutJaggedness(),
        noiseRouter.finalDensity(), noiseRouter.veinToggle(), noiseRouter.veinRidged(),
        noiseRouter.veinGap());
  }

  public NoiseRouter build() {
    return new NoiseRouter(this.barrierNoise, this.fluidLevelFloodednessNoise,
        this.fluidLevelSpreadNoise, this.lavaNoise, this.temperature, this.vegetation,
        this.continents, this.erosion, this.depth, this.ridges,
        this.initialDensityWithoutJaggedness, this.finalDensity, this.veinToggle, this.veinRidged,
        this.veinGap);
  }

  public NoiseRouterBuilder barrierNoise(DensityFunction barrierNoise) {
    this.barrierNoise = barrierNoise;
    return this;
  }

  public NoiseRouterBuilder fluidLevelFloodednessNoise(DensityFunction fluidLevelFloodednessNoise) {
    this.fluidLevelFloodednessNoise = fluidLevelFloodednessNoise;
    return this;
  }

  public NoiseRouterBuilder fluidLevelSpreadNoise(DensityFunction fluidLevelSpreadNoise) {
    this.fluidLevelSpreadNoise = fluidLevelSpreadNoise;
    return this;
  }

  public NoiseRouterBuilder lavaNoise(DensityFunction lavaNoise) {
    this.lavaNoise = lavaNoise;
    return this;
  }

  public NoiseRouterBuilder temperature(DensityFunction temperature) {
    this.temperature = temperature;
    return this;
  }

  public NoiseRouterBuilder vegetation(DensityFunction vegetation) {
    this.vegetation = vegetation;
    return this;
  }

  public NoiseRouterBuilder continents(DensityFunction continents) {
    this.continents = continents;
    return this;
  }

  public NoiseRouterBuilder erosion(DensityFunction erosion) {
    this.erosion = erosion;
    return this;
  }

  public NoiseRouterBuilder depth(DensityFunction depth) {
    this.depth = depth;
    return this;
  }

  public NoiseRouterBuilder ridges(DensityFunction ridges) {
    this.ridges = ridges;
    return this;
  }

  public NoiseRouterBuilder initialDensityWithoutJaggedness(
      DensityFunction initialDensityWithoutJaggedness) {
    this.initialDensityWithoutJaggedness = initialDensityWithoutJaggedness;
    return this;
  }

  public NoiseRouterBuilder finalDensity(DensityFunction finalDensity) {
    this.finalDensity = finalDensity;
    return this;
  }

  public NoiseRouterBuilder veinToggle(DensityFunction veinToggle) {
    this.veinToggle = veinToggle;
    return this;
  }

  public NoiseRouterBuilder veinRidged(DensityFunction veinRidged) {
    this.veinRidged = veinRidged;
    return this;
  }

  public NoiseRouterBuilder veinGap(DensityFunction veinGap) {
    this.veinGap = veinGap;
    return this;
  }
}
