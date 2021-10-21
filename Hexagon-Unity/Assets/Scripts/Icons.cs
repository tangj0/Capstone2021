using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Icons : MonoBehaviour
{
    // The text objects that display the balance of water, oxygen, and energy resources
    public Text waterBalanceText;
    public Text oxygenBalanceText;
    public Text energyBalanceText;

    // Variables to store the balance of water, oxygen, and energy resources
    private static int waterBalance = 0;
    private static int oxygenBalance = 0;
    private static int energyBalance = 0; 

    // Start is called before the first frame update
    void Start()
    {
        SetWaterBalance(40);
        SetOxygenBalance(40);
        SetEnergyBalance(30);
    }

    // Update is called once per frame
    void Update()
    {
        // Update the text
        waterBalanceText.text = waterBalance.ToString();
        oxygenBalanceText.text = oxygenBalance.ToString();
        energyBalanceText.text = energyBalance.ToString();
    }

    /*
     * Getter and setter methods
     */

    public static int GetWaterBalance()
    {
        return waterBalance;
    }

    public static void SetWaterBalance(int balance)
    {
        waterBalance = balance;
    }

    public static int GetOxygenBalance()
    {
        return oxygenBalance;
    }

    public static void SetOxygenBalance(int balance)
    {
       oxygenBalance = balance;
    }

    public static int GetEnergyBalance()
    {
        return energyBalance;
    }

    public static void SetEnergyBalance(int balance)
    {
        energyBalance = balance;
    }
}
