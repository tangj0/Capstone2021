using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Biome : MonoBehaviour
{
    public BiomeType biomeType = BiomeType.biome1;

    public enum BiomeType
    {
        biome1,
        biome2,
        biome3,
        biome4,
        biome5,
        biome6,
        biome7,
        biome8,
        biome9,
        biome10
    }

    private static Color color1 = Color.cyan;
    private static Color color2 = Color.white;
    private static Color color3 = Color.black;
    private static Color color4 = Color.clear;
    private static Color color5 = Color.red;
    private static Color color6 = Color.green;
    private static Color color7 = Color.grey;
    private static Color color8 = Color.yellow;
    private static Color color9 = Color.magenta;
    private static Color color10 = Color.blue;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        switch (biomeType)
        {
            case BiomeType.biome1:
                CameraSettings.SetBackgroundColor(color1);
                break;
            case BiomeType.biome2:
                CameraSettings.SetBackgroundColor(color2);
                break;
            case BiomeType.biome3:
                CameraSettings.SetBackgroundColor(color3);
                break;
            case BiomeType.biome4:
                CameraSettings.SetBackgroundColor(color4);
                break;
            case BiomeType.biome5:
                CameraSettings.SetBackgroundColor(color5);
                break;
            case BiomeType.biome6:
                CameraSettings.SetBackgroundColor(color6);
                break;
            case BiomeType.biome7:
                CameraSettings.SetBackgroundColor(color7);
                break;
            case BiomeType.biome8:
                CameraSettings.SetBackgroundColor(color8);
                break;
            case BiomeType.biome9:
                CameraSettings.SetBackgroundColor(color9);
                break;
            case BiomeType.biome10:
                CameraSettings.SetBackgroundColor(color10);
                break;
        }
    }
}
