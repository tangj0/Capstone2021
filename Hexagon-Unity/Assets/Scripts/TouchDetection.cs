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
    private List<int[]> allTilemapCoordinates;
    private List<int[]> grassTilemapCoordinates;
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

        // Fill in tilemapCoordinates
        allTilemapCoordinates = new List<int[]>();
        for (int x = MIN_GRID_X + 1; x <= MAX_GRID_X; x++)
        {
            for (int y = MIN_GRID_Y + 2; y <= MAX_GRID_Y; y++)
            {
                if (x == MIN_GRID_X + 1 && (y >= 2 || y <= 5))
                {
                    continue;
                }
                allTilemapCoordinates.Add(new int[] { x, y });
            }
        }
        allTilemapCoordinates.RemoveAll(c => c[0] == -3 && c[1] == 4);
        allTilemapCoordinates.RemoveAll(c => c[0] == -3 && c[1] == 5);
        allTilemapCoordinates.RemoveAll(c => c[0] == -2 && c[1] == 5);
        allTilemapCoordinates.RemoveAll(c => c[0] == 5 && c[1] == -3);
        grassTilemapCoordinates = new List<int[]>(allTilemapCoordinates);
        grassTilemapCoordinates.Add(new int[] { -4 , -4});
        grassTilemapCoordinates.Add(new int[] { -3, -4 });
        grassTilemapCoordinates.Add(new int[] { -2, -4 });
        grassTilemapCoordinates.Add(new int[] { -1, -4 });
        grassTilemapCoordinates.Add(new int[] { 0, -4 });
        grassTilemapCoordinates.Add(new int[] { 1, -4 });
        grassTilemapCoordinates.Add(new int[] { 2, -4 });
        grassTilemapCoordinates.Add(new int[] { -4, 2 });
        grassTilemapCoordinates.Add(new int[] { -4, 1 });
        grassTilemapCoordinates.Add(new int[] { -4, 0 });
        grassTilemapCoordinates.Add(new int[] { -4, -1 });
        grassTilemapCoordinates.Add(new int[] { -4, -2 });
        grassTilemapCoordinates.Add(new int[] { -4, -3 });
        grassTilemapCoordinates.Add(new int[] { -5, 1 });
        grassTilemapCoordinates.Add(new int[] { -5, 0 });
        grassTilemapCoordinates.Add(new int[] { -5, -1 });
        grassTilemapCoordinates.Add(new int[] { -5, -2 });
        grassTilemapCoordinates.Add(new int[] { -5, -3 });
        grassTilemapCoordinates.Add(new int[] { -5, -4 });
        grassTilemapCoordinates.Add(new int[] { -5, -5 });
        grassTilemapCoordinates.Add(new int[] { -4, -5 });
        grassTilemapCoordinates.Add(new int[] { -3, -5 });
        grassTilemapCoordinates.Add(new int[] { -2, -5 });
        grassTilemapCoordinates.Add(new int[] { -1, -5 });
        grassTilemapCoordinates.Add(new int[] { 0, -5 });
        grassTilemapCoordinates.Add(new int[] { 1, -5 });
        grassTilemapCoordinates.RemoveAll(c => c[0] == 4 && c[1] == -3);
        grassTilemapCoordinates.RemoveAll(c => c[0] == 5 && c[1] == -2);
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
                        if (Inventory.SpendGrass())
                        {
                            for (int dx = -3; dx <= 3; dx++)
                            {
                                for (int dy = -3; dy <= 3; dy++)
                                {
                                    Vector3Int setCellPos = new Vector3Int(cellPos.x + dx, cellPos.y + dy, 0);
                                    if (grassTilemapCoordinates.Exists(c => c[0] == setCellPos.x && c[1] == setCellPos.y))
                                    {
                                        tilemap.SetTile(setCellPos, grassTile);
                                    }
                                }
                            }
                        }
                        grassTouched = false;
                    }
                    else if (plantTouched)
                    {
                        if (Inventory.SpendPlant())
                        {
                            GameObject obj = Instantiate(plant, grid.CellToWorld(cellPos), Quaternion.identity);
                            obj.transform.SetParent(canvas);
                        }
                        plantTouched = false;
                    }
                    else if (treeTouched)
                    {
                        if (Inventory.SpendTree())
                        {
                            GameObject obj = Instantiate(tree, grid.CellToWorld(cellPos), Quaternion.identity);
                            obj.transform.SetParent(canvas);
                        }
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

        return allTilemapCoordinates.Exists(c => c[0] == (int) gridPos.x && c[1] == (int) gridPos.y);
    }
}
