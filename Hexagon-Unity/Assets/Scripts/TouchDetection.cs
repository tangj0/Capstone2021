using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Tilemaps;
using System;

public class TouchDetection : MonoBehaviour
{
    // Canvas
    public Transform canvas;

    // Grid and tilemap
    public GridLayout grid;
    public Tilemap tilemap;
    public TileBase groundTile;
    public TileBase grassTile;
    private bool[,] tiles;
    private int MIN_GRID_X = -5; // Tilemap coordinates
    private int MAX_GRID_X = 5;
    private int MIN_GRID_Y = -5;
    private int MAX_GRID_Y = 5;

    // Inventory items
    public GameObject grass;
    public GameObject plant;
    public GameObject tree;
    private int PLANT_MIN_X = 13; // World coordinates
    private int PLANT_MAX_X = 66;
    private int PLANT_MIN_Y = 17;
    private int PLANT_MAX_Y = 67;
    private int GRASS_MIN_X = 85; // World coordinates
    private int GRASS_MAX_X = 135;
    private int GRASS_MIN_Y = 17;
    private int GRASS_MAX_Y = 67;
    private int TREE_MIN_X = 157; // World coordinates
    private int TREE_MAX_X = 206;
    private int TREE_MIN_Y = 17;
    private int TREE_MAX_Y = 67;
    bool grassTouched = false;
    bool plantTouched = false;
    bool treeTouched = false;

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
        tiles = new bool[MAX_GRID_X - MIN_GRID_X + 1, MAX_GRID_Y - MIN_GRID_Y + 1];
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            Vector3 worldPosFloat = Input.mousePosition;
            Vector3Int worldPos = new Vector3Int((int) worldPosFloat.x, (int) worldPosFloat.y, (int) worldPosFloat.z);
            Vector3Int cellPos = grid.WorldToCell(worldPos);
            Debug.Log(worldPos);
            Debug.Log("$ " + cellPos);

            switch(GetItemTouched(worldPos.x, worldPos.y))
            {
                case ItemTouched.grass:
                    grassTouched = true;
                    break;
                case ItemTouched.plant:
                    plantTouched = true;
                    break;
                case ItemTouched.tree:
                    treeTouched = true;
                    break;
                case ItemTouched.tile:
                    if (grassTouched) {
                        tilemap.SetTile(cellPos, grassTile);
                        grassTouched = false;
                    }
                    else if (plantTouched)
                    {
                        GameObject obj = Instantiate(plant, grid.CellToWorld(cellPos), Quaternion.identity);
                        obj.transform.SetParent(canvas);
                        plantTouched = false;
                    }
                    else if (treeTouched)
                    {
                        GameObject obj = Instantiate(tree, grid.CellToWorld(cellPos), Quaternion.identity);
                        obj.transform.SetParent(canvas);
                        treeTouched = false;
                    }
                    break;
                default:
                    grassTouched = false;
                    plantTouched = false;
                    treeTouched = false;
                    break;
            }
        }
    }

    // Getter and setter methods for the tiles matrix
    private bool GetTiles(int x, int y)
    {
        try
        {
            return tiles[x + MIN_GRID_X, y + MIN_GRID_Y];
        } catch (IndexOutOfRangeException e) {
            return false;
        }
    }

    private bool SetTiles(int x, int y, bool b)
    {
        try
        {
            tiles[x + MIN_GRID_X, y + MIN_GRID_Y] = b;
            return true;
        } catch (IndexOutOfRangeException e)
        {
            return false;
        }
    }

    // Helper function to determine which item was touched
    private ItemTouched GetItemTouched(int x, int y) 
    {
        if (IsGrassTouched(x, y))
        {
            return ItemTouched.grass;
        }
        else if (IsTreeTouched(x, y))
        {
            return ItemTouched.tree;
        }
        else if (IsPlantTouched(x, y))
        {
            return ItemTouched.plant;
        }
        else if (IsTileTouched(x, y))
        {
            return ItemTouched.tile;
        }
        else
        {
            return ItemTouched.other;
        }
    }

    // Helper functions to determine if an inventory item was touched
    // Uses world coordinates
    private bool IsGrassTouched(int x, int y)
    {
        return x >= GRASS_MIN_X && x <= GRASS_MAX_X && y >= GRASS_MIN_Y && y <= GRASS_MAX_Y;
    }

    private bool IsTreeTouched(int x, int y)
    {
        return x >= TREE_MIN_X && x <= TREE_MAX_X && y >= TREE_MIN_Y && y <= TREE_MAX_Y;
    }

    private bool IsPlantTouched(int x, int y)
    {
        return x >= PLANT_MIN_X && x <= PLANT_MAX_X && y >= PLANT_MIN_Y && y <= PLANT_MAX_Y;
    }

    // Helper function to determine if a tile is touched
    // Uses world coordinates
    private bool IsTileTouched(int x, int y)
    {
        Vector3 worldPos = new Vector3(x, y, 0);
        Vector3 gridPos = grid.WorldToCell(worldPos);

        return gridPos.x >= MIN_GRID_X && gridPos.x <= MAX_GRID_X && gridPos.y >= MIN_GRID_Y && gridPos.y <= MAX_GRID_Y;
    }
}
