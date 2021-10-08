using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TouchDetection : MonoBehaviour
{
    public GridLayout grid;

    private enum ItemTouched
    {
        plant,
        grass,
        tree,
        tile,
        other
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Debug.Log(Input.mousePosition);
            Debug.Log("$ " + grid.WorldToCell(Input.mousePosition));
        }
    }

    private ItemTouched GetItemTouched() 
    {
        return ItemTouched.plant;
    }
}
