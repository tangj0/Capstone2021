using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Biome : MonoBehaviour
{
    public BiomeType biomeType = BiomeType.Temperate;

    public enum BiomeType
    {
        Temperate,
        SteppeGrassland,
        Bushland,
        Borealis,
        Greenland,
        TropicalRainforest
    }

    private static Color TemperateColor = new Color(255f / 255, 191f / 255, 128f / 255);
    private static Color SteppedGrasslandColor = new Color(230f / 255, 255f / 255, 153f / 255);
    private static Color BushlandColor = new Color(172f / 255, 230f / 255, 0f / 255);
    private static Color BorealisColor = new Color(0f / 255, 255f / 255, 204f / 255);
    private static Color GreenlandColor = new Color(230f / 255, 255f / 255, 255f / 255);
    private static Color TropicalRainforestColor = new Color(0f / 255, 153f / 255, 51f / 255);

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        switch (biomeType)
        {
            case BiomeType.Temperate:
                CameraSettings.SetBackgroundColor(TemperateColor);
                break;
            case BiomeType.SteppeGrassland:
                CameraSettings.SetBackgroundColor(SteppedGrasslandColor);
                break;
            case BiomeType.Bushland:
                CameraSettings.SetBackgroundColor(BushlandColor);
                break;
            case BiomeType.Borealis:
                CameraSettings.SetBackgroundColor(BorealisColor);
                break;
            case BiomeType.Greenland:
                CameraSettings.SetBackgroundColor(GreenlandColor);
                break;
            case BiomeType.TropicalRainforest:
                CameraSettings.SetBackgroundColor(TropicalRainforestColor);
                break;
        }
    }
}
