using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Inventory : MonoBehaviour
{
    public static readonly int GRASS_WATER_COST = 2;
    public static readonly int GRASS_OXYGEN_COST = 2;
    public static readonly int GRASS_ENERGY_COST = 2;
    public static readonly int PLANT_WATER_COST = 1;
    public static readonly int PLANT_OXYGEN_COST = 2;
    public static readonly int PLANT_ENERGY_COST = 0;
    public static readonly int TREE_WATER_COST = 2;
    public static readonly int TREE_OXYGEN_COST = 2;
    public static readonly int TREE_ENERGY_COST = 1;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    // Resources spend functions
    public static bool SpendGrass()
    {
        if (GRASS_WATER_COST > Icons.GetWaterBalance() || GRASS_OXYGEN_COST > Icons.GetOxygenBalance() || GRASS_ENERGY_COST > Icons.GetEnergyBalance())
        {
            return false;
        }

        Icons.SetWaterBalance(Icons.GetWaterBalance() - GRASS_WATER_COST);
        Icons.SetOxygenBalance(Icons.GetOxygenBalance() - GRASS_OXYGEN_COST);
        Icons.SetEnergyBalance(Icons.GetEnergyBalance() - GRASS_ENERGY_COST);

        return true;
    }

    public static bool SpendPlant()
    {
        if (PLANT_WATER_COST > Icons.GetWaterBalance() || PLANT_OXYGEN_COST > Icons.GetOxygenBalance() || PLANT_ENERGY_COST > Icons.GetEnergyBalance())
        {
            return false;
        }

        Icons.SetWaterBalance(Icons.GetWaterBalance() - PLANT_WATER_COST);
        Icons.SetOxygenBalance(Icons.GetOxygenBalance() - PLANT_OXYGEN_COST);
        Icons.SetEnergyBalance(Icons.GetEnergyBalance() - PLANT_ENERGY_COST);

        return true;
    }

    public static bool SpendTree()
    {
        if (TREE_WATER_COST > Icons.GetWaterBalance() || TREE_OXYGEN_COST > Icons.GetOxygenBalance() || TREE_ENERGY_COST > Icons.GetEnergyBalance())
        {
            return false;
        }

        Icons.SetWaterBalance(Icons.GetWaterBalance() - TREE_WATER_COST);
        Icons.SetOxygenBalance(Icons.GetOxygenBalance() - TREE_OXYGEN_COST);
        Icons.SetEnergyBalance(Icons.GetEnergyBalance() - TREE_ENERGY_COST);

        return true;
    }
}
